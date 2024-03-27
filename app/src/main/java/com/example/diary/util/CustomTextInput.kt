package com.example.diary.util

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Composable
fun CustomTextInput(hint: String, textState: MutableState<String>, readOnly: Boolean) {
    BasicTextField(
        value = textState.value,
        onValueChange = { textState.value = it },
        textStyle = TextStyle(color = Color.Black),
        modifier = Modifier.fillMaxWidth(),
        readOnly = readOnly,
        decorationBox = { innerTextField ->
            if (textState.value.isEmpty()) {
                Text(text = hint, style = TextStyle(color = Color.Gray))
            }
            innerTextField()
        }
    )
}