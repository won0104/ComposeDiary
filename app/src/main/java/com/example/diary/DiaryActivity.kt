package com.example.diary

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.diary.model.DiaryDatabase
import com.example.diary.model.OfflineDiariesRepository
import com.example.diary.ui.BaseScreen
import com.example.diary.ui.DiaryViewModel
import com.example.diary.ui.DiaryViewModelFactory
import com.example.diary.ui.theme.DiaryTheme
import java.util.Calendar
import java.util.Date

class DiaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val diaryDao = DiaryDatabase.getDatabase(application).diaryDao()
        val repository = OfflineDiariesRepository(diaryDao)
        val viewModelFactory = DiaryViewModelFactory(repository)
        val diaryViewModel = ViewModelProvider(this, viewModelFactory)[DiaryViewModel::class.java]

        setContent {
            val navController = rememberNavController()
            DiaryTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    BaseScreen(navController, diaryViewModel)
                }
            }
        }
    }
}

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
