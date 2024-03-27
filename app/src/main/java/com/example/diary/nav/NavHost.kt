package com.example.diary.nav

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.diary.ui.DiaryListScreen
import com.example.diary.vm.DiaryViewModel
import com.example.diary.ui.EntryForm
import java.time.LocalDateTime

@Composable
fun SetupNavHost(
    navController: NavHostController,
    viewModel: DiaryViewModel,
    selectedDate: LocalDateTime
) {
    NavHost(
        navController = navController,
        startDestination = "list"
    ) {
        composable("list") {
            DiaryListScreen(viewModel,
                onDiaryClick = { diaryId, diaryDate ->
                    viewModel.updateSelectedDate(diaryDate)
                    navController.navigate("detail/$diaryId")
                }
            ) { navController.navigate("create")  ; viewModel.updateSelectedDate(LocalDateTime.now())}
        }
        composable("create") {
            EntryForm(
                viewModel,
                id = -1,
                readOnly = false,
                update = false,
                selectedDate,
                navController
            ) {}
        }
        composable(
            "detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType }) // 인자 정의
        ) { backStackEntry ->
            // 인자 값을 가져와서 사용
            val id = backStackEntry.arguments?.getInt("id") ?: return@composable
            EntryForm(viewModel = viewModel, id = id,
                readOnly = true,
                update = false,
                selectedDate = selectedDate,
                navController = navController, onNavigateToUpdate = { diaryId ->
                    navController.navigate("update/$diaryId")
                })

        }
        composable(
            "update/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: return@composable
            EntryForm(
                viewModel,
                id,
                readOnly = false,
                update = true,
                selectedDate,
                navController
            ) {}
            BackHandler {
                navController.navigate("list")
            }
        }
    }
}