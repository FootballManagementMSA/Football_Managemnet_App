package com.sejongunivclub.feature_memberinfo.presentation.ui_component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MemberDetailView(modifier: Modifier = Modifier) {

    Column(modifier.fillMaxSize()) {
        Column(modifier.weight(2.5f)) {
            Text("자기소개")

        }
        Column(modifier.weight(2.5f)) {
            Text("나이/신체/포지션")

        }
        Column(modifier.weight(2.5f)) {
            Text("성별")

        }
        Column(modifier.weight(2.5f)) {
            Text("현재 가입된 구단")

        }


    }


}