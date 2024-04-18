package com.sejongunivclub.feature_dialog.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sejongunivclub.ui_component.buttons.DarkButton
import com.sejongunivclub.ui_component.template.DefaultDialog
import com.sejongunivclub.ui_component.template.Header

@Composable
fun ClubJoinDialog() {
    DefaultDialog(
        header = { Header(onDismiss = {}, title = "구단 가입 신청입니다.", userName = "테스트") }
    ) {
        Column {
            Text(text = "자기 소개 입력")
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("자기 소개 글을 입력해주세요.") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
            DarkButton(
                buttonText = "가입 신청",
                textColor = Color.White,
                radius = 20.dp
            ) {

            }
        }
    }
}

@Preview
@Composable
fun ClubJoinDialogPreview() {
    ClubJoinDialog()
}