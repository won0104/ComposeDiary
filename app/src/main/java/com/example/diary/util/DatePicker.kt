package com.example.diary.util

import android.app.DatePickerDialog
import android.content.Context
import java.util.Calendar
import java.util.Date

fun datePicker(context: Context, currentDate: Date, onDateSelected: (Date) -> Unit) {
    val calendar = Calendar.getInstance().apply { time = currentDate }
    DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            // 사용자가 날짜를 선택하면 콜백 함수를 호출합니다.
            val selectedCalendar = Calendar.getInstance().apply {
                set(year, month, dayOfMonth)
            }
            onDateSelected(selectedCalendar.time) // 선택된 날짜를 반환합니다.
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    ).show()
}
