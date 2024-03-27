package com.example.diary.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.diary.model.Diary
import com.example.diary.model.FakeDiariesRepository
import com.example.diary.ui.theme.DiaryTheme
import com.example.diary.vm.DiaryViewModel
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import kotlin.math.abs

@Composable
fun DiaryListScreen(viewModel: DiaryViewModel, onDiaryClick: (Int, LocalDateTime) -> Unit, onNavigateToCreate:()->Unit) {
    // LiveData를 관찰하여 UI 업데이트
    val diaries by viewModel.getAllDiaries.observeAsState(initial = emptyList())
    val listState = rememberLazyListState()

    val selectedDate by viewModel.selectedDate.observeAsState(LocalDateTime.now())

    // 가장 가까운 날짜의 일기 인덱스 찾기
    val closestIndex = diaries.minByOrNull { diary ->
        abs(ChronoUnit.DAYS.between(diary.createdDate.toLocalDate(), selectedDate.toLocalDate()))
    }?.let { diary ->
        diaries.indexOf(diary)
    } ?: 0


    LaunchedEffect(key1 = selectedDate,key2 = diaries) {
        listState.scrollToItem(closestIndex)
    }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        LazyColumn(state = listState, modifier = Modifier.weight(0.842f)) {
            itemsIndexed(diaries, key = { _, diary -> diary.id }) { _, diary: Diary ->
                DiaryCard(diary = diary, onItemClick = { diaryId, diaryDate ->
                    onDiaryClick(diaryId, diaryDate)
                })
            }
        }
        Spacer(modifier = Modifier.weight(0.038f))
        Column(modifier = Modifier.weight(0.120f)) {
            OneButton(buttonText = "일기 쓰기", onNavigateToCreate)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DiaryListPreview() {
    DiaryTheme {
        val fakeRepository = FakeDiariesRepository()
        val diaryViewModel = DiaryViewModel(fakeRepository)
        DiaryListScreen(
            viewModel = diaryViewModel,
            onDiaryClick = { _, _ -> },
            onNavigateToCreate = { }
        )
    }
}
