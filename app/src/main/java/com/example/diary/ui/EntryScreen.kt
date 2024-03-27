package com.example.diary.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diary.ui.theme.DiaryTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.diary.R
import com.example.diary.model.FakeDiariesRepository
import com.example.diary.ui.common.OneButton
import com.example.diary.ui.common.CustomTextInput
import com.example.diary.vm.DiaryViewModel
import java.time.LocalDateTime

@Composable
fun EntryForm(
    viewModel: DiaryViewModel,
    id: Int,
    readOnly: Boolean,
    update: Boolean,
    selectedDate: LocalDateTime,
    navController: NavHostController,
    onNavigateToUpdate: (Int) -> Unit
) {
    val context = LocalContext.current // 현재 Compose 환경의 Context를 가져옵니다.
    val title = remember { mutableStateOf("") }
    val content = remember { mutableStateOf("") }
    val diary by viewModel.getDiary(id).observeAsState() //없으면 null

    // diary 객체의 변경 감지
    LaunchedEffect(diary) {
        title.value = diary?.title ?: ""
        content.value = diary?.content ?: ""
    }

    // readonly True : 상세 보기, False : 업데이트, 쓰기
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Text
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.25f)
                .clip(RoundedCornerShape(15.dp))
                .background(Color.White)
        )
        {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = stringResource(id = R.string.title),
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.padding(5.dp))
                CustomTextInput(stringResource(id = R.string.input_title_hint), title, readOnly)
            }
        }
        Spacer(modifier = Modifier.weight(0.028f))

        // Content
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.672f)
                .clip(RoundedCornerShape(15.dp))
                .background(Color.White)
        )
        {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = stringResource(id = R.string.content),
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.padding(5.dp))
                CustomTextInput(stringResource(id = R.string.input_content_hint), content, readOnly)
            }
        }
        Spacer(modifier = Modifier.weight(0.028f))

        /** Button
         *  dialogType 0 : 저장(쓰기) , 1 : 삭제, 2 : 수정
         *
         */
        Column(modifier = Modifier.weight(0.120f)) {
            var dialogType by remember { mutableIntStateOf(0) }
            var showDialog by remember { mutableStateOf(false) }

            // 버튼 유형 설정
            if (readOnly && !(update)) { // 상세 보기 화면일 때
                diary?.id?.let {
                    UpdateDeleteButton(
                        id = id,
                        diaryDelete = {
                            dialogType = 1
                            showDialog = true
                        },
                        onNavigateToUpdate = onNavigateToUpdate
                    )
                }
            } else if (!readOnly && update) { // 수정하기 화면일 때
                OneButton(buttonText = stringResource(id = R.string.update)) {
                    dialogType = 2
                    showDialog = true
                }
            } else { // 일기 쓰기 (저장) 화면일 때
                OneButton(buttonText = stringResource(id = R.string.save)) {
                    val message: String
                    if (title.value.isNotBlank()) {
                        message = context.getString(R.string.saved_message,title.value)
                        viewModel.insertDiary(title.value, content.value, selectedDate)
                        viewModel.updateSelectedDate(selectedDate)
                        navController.navigate("list")
                    }else{
                        message = context.getString(R.string.error_empty_title)
                    }
                    Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
                }
            }

            //Dialog Type 설정
            if (dialogType == 1) { // 삭제
                GenericDialog(
                    showDialog,
                    stringResource(id = R.string.dialog_delete),
                    onDismissRequest = { showDialog = false },
                    stringResource(id = R.string.delete),
                    stringResource(id = R.string.cancel)
                ) {
                    viewModel.deleteDiary(id) // 여기서 id는 TwoButtonFragment에 전달된 id를 사용
                    Toast.makeText(
                        context,
                        context.getString(R.string.deleted_message, title.value),
                        Toast.LENGTH_LONG
                    ).show()
                    navController.navigate("list")
                }
            } else if (dialogType == 2) { // 수정
                GenericDialog(
                    showDialog,
                    stringResource(id = R.string.dialog_update),
                    onDismissRequest = { showDialog = false },
                    stringResource(id = R.string.update_button),
                    stringResource(id = R.string.cancel)
                ) {
                    viewModel.updateDiary(id, title.value, content.value, selectedDate)
                    Toast.makeText(
                        context,
                        context.getString(R.string.updated_message, title.value),
                        Toast.LENGTH_SHORT
                    ).show()
                    navController.navigate("list")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EntryFragmentPreview() {
    val fakeRepository = FakeDiariesRepository()
    val diaryViewModel = DiaryViewModel(fakeRepository)
    val navController = rememberNavController()
    DiaryTheme {
        EntryForm(diaryViewModel, 1, false, false, LocalDateTime.now(), navController) {}
    }
}

