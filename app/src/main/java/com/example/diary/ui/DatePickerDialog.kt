package com.example.diary.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diary.ui.theme.DiaryTheme
import com.example.diary.ui.theme.highlights_blue
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.painterResource
import com.example.diary.R
import com.example.diary.util.datePicker
import com.example.diary.model.FakeDiariesRepository
import com.example.diary.ui.theme.gray_light
import com.example.diary.vm.DiaryViewModel
import java.time.LocalDateTime
import java.time.ZoneId

@Composable
fun DatePickerDialog(
    select: Boolean,
    viewModel: DiaryViewModel,
    onDateSelected: (LocalDateTime) -> Unit // 뷰 모델 값 업데이트
) {
    val context = LocalContext.current

    val dateFormat = if (select) {
        SimpleDateFormat("yyyy년 MM월 dd일", Locale.getDefault())
    } else {
        SimpleDateFormat("yyyy년 MM월", Locale.getDefault())
    }

    val selectedDateTime by viewModel.selectedDate.observeAsState(LocalDateTime.now())

    var selectDate by remember {
        mutableStateOf(
            dateFormat.format(
                Date.from(
                    selectedDateTime.atZone(
                        ZoneId.systemDefault()
                    ).toInstant()
                )
            )
        )
    }

    if (select) {
        Row(modifier = Modifier
            .height(18.dp)
            .wrapContentWidth()
            .clickable {
                // DatePickerDialog 표시
                datePicker(context, dateFormat.parse(selectDate) ?: Date()) { newDate ->
                    selectDate = dateFormat.format(newDate)
                    val newDateTime =
                        LocalDateTime.ofInstant(newDate.toInstant(), ZoneId.systemDefault())
                    onDateSelected(newDateTime)
                }
            }) {
            Box(
                modifier = Modifier
                    .height(18.dp)
                    .wrapContentWidth()
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .width(115.dp) // 아래 있는 Text 크기에 맞춰서 변경 가능 한지
                        .matchParentSize()
                        .height(5.dp)
                        .background(highlights_blue)
                )
                Text(
                    text = selectDate,
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.TopCenter)
                )
            }
            Spacer(modifier = Modifier.width(5.dp))

            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Bottom
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ico_calendar),
                    contentDescription = "Calendar",
                )
            }
        }
    } else {
        fun LocalDateTime.toDate(): Date =
            Date.from(this.atZone(ZoneId.systemDefault()).toInstant())

        val buttonText = dateFormat.format(selectedDateTime.toDate())

        Box(
            modifier = Modifier
                .height(27.dp)
                .width(90.dp)
        ) {
            Button(
                onClick = {
                    datePicker(context, dateFormat.parse(selectDate) ?: Date()) { newDate ->
                        selectDate = dateFormat.format(newDate)
                        val newDateTime =
                            LocalDateTime.ofInstant(newDate.toInstant(), ZoneId.systemDefault())
                        onDateSelected(newDateTime)
                    }
                },
                modifier = Modifier
                    .fillMaxSize(),
                shape = RoundedCornerShape(30.dp),
                border = BorderStroke(1.dp, color = gray_light),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                )
            ) {
            }
            Text(
                text = buttonText,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally) // 가로 중앙 정렬
                    .fillMaxHeight()
                    .wrapContentHeight(Alignment.CenterVertically)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DatePickerDialogPreview() {
    val fakeRepository = FakeDiariesRepository()
    val diaryViewModel = DiaryViewModel(fakeRepository)

    DiaryTheme {
        DatePickerDialog(
            select = false,
            diaryViewModel,
            onDateSelected = { date ->
                diaryViewModel.updateSelectedDate(date)
            }
        )
    }
}