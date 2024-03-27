package com.example.diary.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.diary.R
import com.example.diary.ui.theme.DiaryTheme
import com.example.diary.ui.theme.toast_button_blue

@Composable
fun GenericDialog(
    showDialog: Boolean,
    title: String,
    onDismissRequest: () -> Unit,
    positiveText: String,
    negativeString: String,
    onClickOk: () -> Unit
) {
    if (showDialog) {
        Dialog(onDismissRequest = onDismissRequest) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(15.dp)),
            ) {
                Column(
                    modifier = Modifier.padding(30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = { onClickOk() },
                        modifier = Modifier
                            .size(270.dp, 60.dp)
                            .padding(10.dp),
                        shape = RoundedCornerShape(15.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = toast_button_blue,
                            contentColor = Color.Black
                        )
                    ) {
                        Text(text = positiveText, style = MaterialTheme.typography.titleSmall)
                    }

                    Button(
                        onClick = { onDismissRequest() },
                        modifier = Modifier
                            .size(250.dp, 40.dp),
                        shape = RoundedCornerShape(15.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        )
                    ) {
                        Text(text = negativeString, style = MaterialTheme.typography.bodyLarge)
                    }
                }

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GenericDialogPreview() {
    val title = stringResource(id = R.string.delete_dialog_title)
    val positiveText = stringResource(id = R.string.delete_dialog_positive_text)
    val negativeText = stringResource(id = R.string.dialog_negative_text)
    DiaryTheme {
        GenericDialog(true, title, {}, positiveText, negativeText) {}
    }
}
