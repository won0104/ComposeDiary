package com.example.diary.ui

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalView

@Composable
fun KeyboardHidingSurface(content: @Composable () -> Unit) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val view = LocalView.current

    // Composable 내에서 포인터 입력을 처리하기 위한 Modifier
    val modifier = Modifier
        .pointerInput(Unit) {
            detectTapGestures(
                onPress = {
                    // Composable 함수 내부에서 키보드 숨김 처리
                    focusManager.clearFocus()
                    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(view.windowToken, 0)
                }
            )
        }

    Box(modifier = modifier.fillMaxSize()) {
        content()
    }
}

