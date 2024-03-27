package com.example.diary

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
import com.example.diary.vm.DiaryViewModel
import com.example.diary.vm.DiaryViewModelFactory
import com.example.diary.ui.theme.DiaryTheme

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
