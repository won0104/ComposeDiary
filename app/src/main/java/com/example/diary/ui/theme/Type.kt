package com.example.diary.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography = Typography(

    // 페이지 이름
    titleLarge = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        color = Color.Black
    ),

    // 입력 필드 제목
    titleMedium = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        color =label_blue
    ),

    // DatePicker - 아이콘 ver
    labelMedium= TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        color = Color.Black
    ),

    // DatePicker - 버튼 ver
    labelSmall = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        color = Color.Black
    ),

    //
    labelLarge = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,

    ),

    // 리스트 제목
    bodyLarge = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        color =Color.Black
    ),

    // 입력 필드 hintText
    bodySmall = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.Normal,
        fontSize=14.sp,
        color = hint_text_gray
    ),


    // 리스트 context
    bodyMedium = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.Normal,
        fontSize=14.sp,
        color =Color.Black
    ),

    // 리스트 날짜
    headlineMedium = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        color = Color.Black
    ),

    //긍정 버튼
    titleSmall = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        color = Color.Black
    )
)