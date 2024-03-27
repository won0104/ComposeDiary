package com.example.diary.ui

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diary.ui.theme.DiaryTheme
import com.example.diary.ui.theme.accent_blue

@Composable
fun OneButton(buttonText: String, pressButton: () -> Unit) {
    Button(
        onClick = { pressButton() },
        modifier = Modifier
            .width(333.dp)
            .height(50.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = accent_blue,
            contentColor = Color.White
        ),
    ) {
        Text(text = buttonText, style = MaterialTheme.typography.labelLarge)
    }
}


@Preview(showBackground = true)
@Composable
fun OneButtonPreview() {
    DiaryTheme {
        OneButton(buttonText = "일기 쓰기") {
        }
    }
}
