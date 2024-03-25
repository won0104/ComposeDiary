package com.example.diary.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.diary.R
import com.example.diary.SetupNavHost
import com.example.diary.model.FakeDiariesRepository
import com.example.diary.ui.theme.DiaryTheme
import com.example.diary.ui.theme.primary_background_color
import java.time.LocalDateTime

@Composable
fun BaseScreen(
    navController: NavHostController,
    viewModel: DiaryViewModel,
) {
    // 현재 네비게이션 경로를 관찰
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    val selectedDate by viewModel.selectedDate.observeAsState(LocalDateTime.now())

    // 현재 경로에 따라 페이지 타이틀 결정
    val pageTitle = when {
        currentRoute?.startsWith("list") == true -> R.string.diary_list
        else -> R.string.diary_write
    }

    DiaryTheme {
        KeyboardHidingSurface {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(primary_background_color)
                    .padding(30.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                Column(
                    modifier = Modifier
                        .weight(0.17f)
                        .fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.padding(5.dp))
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "인코누스 로고",
                        modifier = Modifier
                            .size(width = 104.dp, height = 22.dp)
                    )
                    Spacer(modifier = Modifier.padding(15.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            text = if (currentRoute?.startsWith("list") == true)
                                stringResource(id = R.string.diary_list)
                            else stringResource(id = R.string.diary_write),
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                        if (pageTitle == R.string.diary_list) {
                            Box(modifier = Modifier.align(Alignment.CenterVertically)) {
                                DatePickerFragment(
                                    select = false,
                                    viewModel,
                                    onDateSelected = { date ->
                                        viewModel.updateSelectedDate(date)
                                    }
                                )
                            }
                        } else {
                            Box(modifier = Modifier.align(Alignment.CenterVertically)) {
                                DatePickerFragment(
                                    select = true,
                                    viewModel,
                                    onDateSelected = { date ->
                                        viewModel.updateSelectedDate(date)
                                    }
                                )
                            }
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .weight(0.82f)
                        .fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.height(15.dp))
                    SetupNavHost(navController = navController, viewModel = viewModel, selectedDate)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BaseScreenPreview() {
    val fakeRepository = FakeDiariesRepository()
    val diaryViewModel = DiaryViewModel(fakeRepository)
    val navController = rememberNavController()

    DiaryTheme {
        BaseScreen(
            navController, diaryViewModel
        )
    }
}
