package com.example.diary.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diary.model.Diary
import com.example.diary.model.FakeDiariesRepository
import com.example.diary.ui.theme.DiaryTheme
import com.example.diary.ui.theme.highlights_blue
import com.example.diary.vm.DiaryViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun DiaryCard(diary: Diary, onItemClick: (Int, LocalDateTime) -> Unit) {
    val date = diary.createdDate.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"))
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(134.dp)
        .clip(RoundedCornerShape(15.dp)) //둥근 모서리 적용
        .clickable { onItemClick(diary.id, diary.createdDate) } // 클릭 이벤트에 날짜 정보 전달
        .padding(10.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(15.dp),
        ) {
            Box(contentAlignment = Alignment.BottomStart) {
                Box(
                    modifier = Modifier
                        .size(width = 145.dp, height = 9.dp) // 크기 지정
                        .background(highlights_blue)
                )
                Text(
                    text = date,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.TopCenter)
                )
            }
            Spacer(modifier = Modifier.heightIn(5.dp))
            Text(
                text = diary.title,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.heightIn(5.dp))
            Text(
                text = diary.content,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DiaryCardPreview() {
    DiaryTheme {
        val fakeRepository = FakeDiariesRepository()
        val diaryViewModel = DiaryViewModel(fakeRepository)
        val diary = diaryViewModel.getDiary(1).observeAsState().value
        diary?.let {
            //DiaryCard(it) {}
        }
    }
}