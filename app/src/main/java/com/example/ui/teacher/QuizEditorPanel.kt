package com.example.ui.teacher

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.ui.theme.isAppInDarkTheme

data class SheetColumn(val title: String, val weight: Float)

@Composable
fun QuizEditorPanel(
    quizTitleEn: String,
    onDismiss: () -> Unit
) {
    var rows by remember { mutableStateOf(List(3) { List(getColsForQuiz(quizTitleEn).size) { "" } }) }
    val isDark = isAppInDarkTheme()
    val bgCol = if (isDark) Color(0xFF191C1D) else Color.White
    val onBgCol = if (isDark) Color.White else Color.Black
    val borderCol = if (isDark) Color(0xFF33353A) else Color(0xFFE1E3E4)
    val headerCol = if (isDark) Color(0xFF232528) else Color(0xFFF0F2F4)

    val cols = getColsForQuiz(quizTitleEn)

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = bgCol
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                // Header
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Editing: $quizTitleEn",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = onBgCol
                    )
                    Row {
                        IconButton(onClick = onDismiss) {
                            Icon(Icons.Default.Close, contentDescription = "Close", tint = onBgCol)
                        }
                    }
                }

                Divider(color = borderCol)

                // Toolbar
                Row(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(onClick = { rows = rows + listOf(List(cols.size) { "" }) }) {
                        Icon(Icons.Default.Add, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Add Row")
                    }
                    Button(onClick = onDismiss, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E8E62))) {
                        Icon(Icons.Default.Save, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Save Changes")
                    }
                }

                // Spreadsheet Area
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(horizontal = 16.dp)
                ) {
                    val scrollState = rememberScrollState()
                    Column(modifier = Modifier.horizontalScroll(scrollState)) {
                        // Header Row
                        Row(
                            modifier = Modifier
                                .background(headerCol, RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                                .border(1.dp, borderCol, RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                        ) {
                            cols.forEach { col ->
                                Box(
                                    modifier = Modifier
                                        .width(col.weight.dp)
                                        .padding(12.dp)
                                ) {
                                    Text(
                                        text = col.title,
                                        fontWeight = FontWeight.Bold,
                                        color = onBgCol,
                                        fontSize = 14.sp
                                    )
                                }
                            }
                            Box(modifier = Modifier.width(60.dp).padding(12.dp)) {
                                Text("Actions", fontWeight = FontWeight.Bold, color = onBgCol, fontSize = 14.sp)
                            }
                        }

                        // Data Rows
                        LazyColumn(
                            modifier = Modifier
                                .border(1.dp, borderCol, RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp))
                                .heightIn(max = 2000.dp)
                        ) {
                            items(rows.size) { r ->
                                Row(
                                    modifier = Modifier.border(0.5.dp, borderCol),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    cols.forEachIndexed { c, col ->
                                        Box(
                                            modifier = Modifier
                                                .width(col.weight.dp)
                                                .border(0.5.dp, borderCol)
                                                .heightIn(min = 50.dp)
                                                .padding(horizontal = 8.dp),
                                            contentAlignment = Alignment.CenterStart
                                        ) {
                                            BasicTextField(
                                                value = rows[r][c],
                                                onValueChange = { newVal ->
                                                    val newRows = rows.toMutableList()
                                                    val newRow = newRows[r].toMutableList()
                                                    newRow[c] = newVal
                                                    newRows[r] = newRow
                                                    rows = newRows
                                                },
                                                textStyle = TextStyle(color = onBgCol, fontSize = 14.sp),
                                                modifier = Modifier.fillMaxWidth()
                                            )
                                        }
                                    }
                                    Box(
                                        modifier = Modifier
                                            .width(60.dp)
                                            .border(0.5.dp, borderCol)
                                            .heightIn(min = 50.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        IconButton(
                                            onClick = {
                                                val newRows = rows.toMutableList()
                                                newRows.removeAt(r)
                                                rows = newRows
                                            },
                                            modifier = Modifier.size(32.dp)
                                        ) {
                                            Icon(
                                                Icons.Default.Delete,
                                                contentDescription = "Delete Row",
                                                tint = Color.Red,
                                                modifier = Modifier.size(16.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

fun getColsForQuiz(quizTitleEn: String): List<SheetColumn> {
    return when (quizTitleEn) {
        "Lesson Quizzes" -> listOf(
            SheetColumn("Question", 200f),
            SheetColumn("Option A", 120f),
            SheetColumn("Option B", 120f),
            SheetColumn("Option C", 120f),
            SheetColumn("Option D", 120f),
            SheetColumn("Answer", 100f)
        )
        "Fill in the Blanks" -> listOf(
            SheetColumn("Sentence (use [BLANK])", 300f),
            SheetColumn("Correct Word", 150f),
            SheetColumn("Distractors (comma separated)", 200f)
        )
        "Math Solver" -> listOf(
            SheetColumn("Problem", 250f),
            SheetColumn("Answer", 150f)
        )
        "Vocabulary Memory" -> listOf(
            SheetColumn("Word (En)", 200f),
            SheetColumn("Meaning (Bn)", 200f)
        )
        else -> listOf(
            SheetColumn("Field 1", 200f),
            SheetColumn("Field 2", 200f)
        )
    }
}
