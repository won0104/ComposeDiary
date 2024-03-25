package com.example.diary.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
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
fun OneButtonFragment(buttonText: String, pressButton: () -> Unit) {
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

@Composable
fun TwoButtonFragment(id :Int, diaryDelete:() ->Unit, onNavigateToUpdate: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = {diaryDelete()},
            modifier = Modifier
                .weight(0.47f)
                .fillMaxHeight(),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(1.dp, color = accent_blue),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = accent_blue
            ),
        ) {
            Text(text = "삭제", style = MaterialTheme.typography.labelLarge)
        }
        Spacer(modifier = Modifier.weight(0.06f))

        Button(
            onClick = { onNavigateToUpdate(id) },
            modifier = Modifier
                .weight(0.47f)
                .fillMaxHeight(),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = accent_blue,
                contentColor = Color.White
            ),
        ) {
            Text(text = "수정", style = MaterialTheme.typography.labelLarge)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ButtonFragmentPreview() {
    DiaryTheme {
        TwoButtonFragment(1, {},{})
    }
}
