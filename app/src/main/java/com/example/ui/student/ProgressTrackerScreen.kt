package com.example.ui.student

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import com.example.ui.components.DigitTabHeader
import androidx.compose.material.icons.outlined.Notifications

private val ProgressScreenBg: Color @Composable get() = if (isSystemInDarkTheme()) Color(0xFF191C1E) else Color(0xFFF8F9FA)
private val ProgressCardBg: Color @Composable get() = if (isSystemInDarkTheme()) Color(0xFF282A2D) else Color(0xFFFFFFFF)
private val ProgressPrimaryText: Color @Composable get() = if (isSystemInDarkTheme()) Color(0xFFE1E2E4) else Color(0xFF1A1C1E)
private val ProgressSecondaryText: Color @Composable get() = if (isSystemInDarkTheme()) Color(0xFFC4C7C5) else Color(0xFF5E6368)
private val ProgressBorder: Color @Composable get() = if (isSystemInDarkTheme()) Color(0xFF3F4245) else Color(0xFFEEF0F2)

private val ProgressBlueText = Color(0xFF3C3F73)
private val ProgressLavender = Color(0xFFE0E0FF) // For avatar bg

private val ProgressMaths = Color(0xFF54578C)
private val ProgressReading = Color(0xFF006D5B)
private val ProgressScience = Color(0xFF8487BF)
private val ProgressHistory = Color(0xFFD38350)
private val ProgressTeal = Color(0xFF00695C)
private val ProgressTrackColor: Color @Composable get() = if (isSystemInDarkTheme()) Color(0xFF3F4245) else Color(0xFFEEF0F2)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProgressTrackerScreen(
    onNavigateHome: () -> Unit,
    onNavigateLessons: () -> Unit,
    onNavigateQuizzes: () -> Unit,
    onNavigateBack: () -> Unit,
    onNavigateToProfile: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            DigitTabHeader(
                actions = {
                    SharedStudentHeaderActions(onNavigateToProfile = onNavigateToProfile)
                }
            )
        },
        bottomBar = {
            StudentBottomNavBar(
                currentRoute = "Progress",
                onNavigateToHome = onNavigateHome,
                onNavigateToLessons = onNavigateLessons,
                onNavigateToProgress = {},
                onNavigateToQuizzes = onNavigateQuizzes,
                onNavigateToProfile = onNavigateToProfile
            )
        },
        containerColor = ProgressScreenBg
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(), 
            contentPadding = PaddingValues(
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding() + 24.dp
            )
        ) {
            item {
                HeroProfileSection()
            }

            item {
                TopStatsGrid()
            }

            item {
                SubjectProgressSection()
            }
        }
    }
}

@Composable
fun HeroProfileSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape)
                .background(ProgressLavender),
            contentAlignment = Alignment.Center
        ) {
            Text("AM", color = ProgressBlueText, fontWeight = FontWeight.Bold, fontSize = 24.sp)
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                "আয়েশা মাহমুদ", 
                fontSize = 24.sp, 
                fontWeight = FontWeight.Bold, 
                color = ProgressPrimaryText
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.School, contentDescription = null, tint = ProgressSecondaryText, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("Grade 5", fontSize = 14.sp, color = ProgressSecondaryText)
            }
        }
    }
}

@Composable
fun TopStatsGrid() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(bottom = 32.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Card 1
        Surface(
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(32.dp),
            color = ProgressCardBg,
            border = BorderStroke(1.dp, ProgressBorder),
            shadowElevation = 2.dp
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                Text("Avg score", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = ProgressPrimaryText)
                Text("গড় স্কোর", fontSize = 12.sp, color = ProgressSecondaryText)
                Spacer(modifier = Modifier.height(16.dp))
                Text("84%", fontSize = 48.sp, fontWeight = FontWeight.Bold, color = ProgressPrimaryText)
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.TrendingUp, contentDescription = null, tint = ProgressTeal, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("+6 vs last week", fontSize = 12.sp, color = ProgressTeal, fontWeight = FontWeight.Medium)
                }
            }
        }

        // Card 2
        Surface(
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(32.dp),
            color = ProgressCardBg,
            border = BorderStroke(1.dp, ProgressBorder),
            shadowElevation = 2.dp
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                Text("Quizzes done", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = ProgressPrimaryText)
                Text("কুইজ সম্পন্ন", fontSize = 12.sp, color = ProgressSecondaryText)
                Spacer(modifier = Modifier.height(16.dp))
                Text("7", fontSize = 48.sp, fontWeight = FontWeight.Bold, color = ProgressPrimaryText)
                Spacer(modifier = Modifier.height(4.dp))
                Text("of 7 assigned", fontSize = 12.sp, color = ProgressSecondaryText)
            }
        }
    }
}

@Composable
fun SubjectProgressSection() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(32.dp),
        color = ProgressCardBg,
        border = BorderStroke(1.dp, ProgressBorder),
        shadowElevation = 2.dp
    ) {
        Column(modifier = Modifier.padding(vertical = 32.dp, horizontal = 24.dp)) {
            Text(
                "PROGRESS BY SUBJECT / বিষয়ভিত্তিক অগ্রগতি",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = ProgressSecondaryText,
                letterSpacing = 0.5.sp
            )
            Spacer(modifier = Modifier.height(24.dp))
            
            SubjectProgressItem("Maths", "গণিত", 0.84f, ProgressMaths)
            Spacer(modifier = Modifier.height(24.dp))
            SubjectProgressItem("Reading", "পঠন", 0.91f, ProgressReading)
            Spacer(modifier = Modifier.height(24.dp))
            SubjectProgressItem("Science", "বিজ্ঞান", 0.77f, ProgressScience)
            Spacer(modifier = Modifier.height(24.dp))
            SubjectProgressItem("History", "ইতিহাস", 0.69f, ProgressHistory)
        }
    }
}

@Composable
fun SubjectProgressItem(titleEn: String, titleBn: String, progress: Float, color: Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.width(80.dp)) {
            Text(titleEn, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = ProgressPrimaryText)
            Text(titleBn, fontSize = 12.sp, color = ProgressSecondaryText)
        }
        
        Spacer(modifier = Modifier.width(16.dp))
        
        CustomProgressBar(
            progress = progress,
            modifier = Modifier
                .weight(1f)
                .height(12.dp),
            color = color,
            trackColor = ProgressTrackColor
        )
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Text(
            text = "${(progress * 100).toInt()}%",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = color,
            textAlign = TextAlign.End,
            modifier = Modifier.width(32.dp)
        )
    }
}
