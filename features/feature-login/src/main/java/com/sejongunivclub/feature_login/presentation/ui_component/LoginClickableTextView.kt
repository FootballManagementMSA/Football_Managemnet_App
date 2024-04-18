package com.sejongunivclub.feature_login.presentation.ui_component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginClickableTextView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.Center)
    ) {
        Text(
            text = "비밀번호를 잊으셨습니까?",
            color = Color(0xFF857FEB),
            modifier = Modifier
                .clickable {
                }
                .align(Alignment.CenterHorizontally),
            fontSize = 10.sp
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "아직 계정이 없으신가요?",
            color = Color(0xFF857FEB),
            modifier = Modifier
                .clickable {
                }
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp),
            fontSize = 10.sp
        )
    }
}

@Preview
@Composable
fun LoginTextViewPreview() {
    LoginClickableTextView()
}