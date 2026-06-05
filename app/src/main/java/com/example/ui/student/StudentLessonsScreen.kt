package com.example.ui.student

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.DigitTabHeader
import androidx.compose.material.icons.outlined.Notifications

private val LessonScreenBg: Color @Composable get() = if (isSystemInDarkTheme()) Color(0xFF191C1E) else Color(0xFFF8F9FA)
private val LessonCardBg: Color @Composable get() = if (isSystemInDarkTheme()) Color(0xFF282A2D) else Color(0xFFFFFFFF)
private val LessonPrimaryText: Color @Composable get() = if (isSystemInDarkTheme()) Color(0xFFE1E2E4) else Color(0xFF1A1C1E)
private val LessonSecondaryText: Color @Composable get() = if (isSystemInDarkTheme()) Color(0xFFC4C7C5) else Color(0xFF5E6368)
private val LessonBorder: Color @Composable get() = if (isSystemInDarkTheme()) Color(0xFF3F4245) else Color(0xFFEEF0F2)

private val LessonBlueText = Color(0xFF3C3F73)
private val LessonLavender = Color(0xFFE0E0FF)
private val LessonHeroBg = Color(0xFF3C3F73)
private val LessonTeal = Color(0xFF00695C)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentLessonsScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToProgress: () -> Unit,
    onNavigateToQuizzesTab: () -> Unit,
    onNavigateToLesson: () -> Unit,
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
                currentRoute = "Lessons",
                onNavigateToHome = onNavigateToHome,
                onNavigateToLessons = {},
                onNavigateToProgress = onNavigateToProgress,
                onNavigateToQuizzes = onNavigateToQuizzesTab,
                onNavigateToProfile = onNavigateToProfile
            )
        },
        containerColor = LessonScreenBg
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = PaddingValues(
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding() + 24.dp
            )
        ) {
            item {
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Text(
                        text = "বর্তমান পাঠ / Current Lesson",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = LessonPrimaryText
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    LessonsHeroSection()
                }
            }
            item {
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Text(
                        text = "আপনার পাঠসমূহ / Your Lessons",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = LessonBlueText
                    )
                }
            }
            item {
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    LessonItem(titleBn = "বীজগণিত: সূত্রাবলি", titleEn = "Algebra: Formulas", pages = "15", onClick = onNavigateToLesson)
                    LessonItem(titleBn = "পদার্থবিজ্ঞান: গতির নিয়ম", titleEn = "Physics: Laws of Motion", pages = "24", onClick = onNavigateToLesson)
                    LessonItem(titleBn = "বাংলা ব্যাকরণ: কারক", titleEn = "Bangla Grammar: Cases", pages = "12", onClick = onNavigateToLesson)
                }
            }
        }
    }
}

@Composable
fun LessonsHeroSection() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(32.dp),
        color = LessonHeroBg,
        shadowElevation = 4.dp
    ) {
        Box {
            // Background decor
            Box(modifier = Modifier.align(Alignment.Center).offset(x = 24.dp).size(120.dp).border(2.dp, Color.White.copy(alpha = 0.2f), CircleShape), contentAlignment = Alignment.Center) {
                Icon(Icons.Outlined.Description, contentDescription = null, tint = Color.White.copy(alpha = 0.8f), modifier = Modifier.size(48.dp))
            }

            Column(
                modifier = Modifier.padding(24.dp)
            ) {
                Text(
                    "MATH - GEOMETRY",
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    "জ্যামিতি: ত্রিভুজের\nবৈশিষ্ট্য",
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 36.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "Geometry: Properties of Triangles",
                    color = Color.White.copy(alpha = 0.9f),
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(24.dp))
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(containerColor = LessonTeal, contentColor = Color.White),
                        contentPadding = PaddingValues(horizontal = 32.dp, vertical = 14.dp)
                    ) {
                        Icon(Icons.AutoMirrored.Filled.MenuBook, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Read Now", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun LessonItem(titleBn: String, titleEn: String, pages: String, onClick: () -> Unit) {
    Surface(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(32.dp),
        color = LessonCardBg,
        shadowElevation = 2.dp
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(LessonLavender.copy(alpha = 0.5f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Outlined.Description, contentDescription = null, tint = LessonBlueText, modifier = Modifier.size(40.dp))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(titleBn, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = LessonBlueText)
                Spacer(modifier = Modifier.height(2.dp))
                Text(titleEn, fontSize = 12.sp, color = LessonPrimaryText, fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.height(8.dp))
                
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.AutoMirrored.Filled.MenuBook, contentDescription = null, tint = LessonSecondaryText, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("$pages Pages", fontSize = 12.sp, color = LessonSecondaryText, fontWeight = FontWeight.Medium)
                }
                
                Spacer(modifier = Modifier.height(8.dp))
                Box(contentAlignment = Alignment.CenterEnd, modifier = Modifier.fillMaxWidth()) {
                    Button(
                        onClick = onClick,
                        colors = ButtonDefaults.buttonColors(containerColor = LessonTeal, contentColor = Color.White),
                        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp)
                    ) {
                        Text("Read Now", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }
                }
            }
        }
    }
}
