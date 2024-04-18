package com.sejongunivclub.feature_dialog.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sejongunivclub.core.model.UserInfo
import com.sejongunivclub.feature_dialog.presentation.viewmodel.DialogViewModel
import com.sejongunivclub.ui_component.buttons.DarkButton
import com.sejongunivclub.ui_component.template.DefaultDialog
import com.sejongunivclub.ui_component.buttons.DialogCustomGradientButton
import com.sejongunivclub.ui_component.template.Header
import com.sejongunivclub.ui_component.HorizontalSpacer
import com.sejongunivclub.ui_component.values.gradientColorsList

@Composable
fun ClubJoinCheckDialog(
    userInfo: UserInfo,
    onDismiss: () -> Unit,
    viewModel: DialogViewModel = hiltViewModel()
) {
    DefaultDialog(
        header = { Header(onDismiss = onDismiss, title = "멤버 가입 신청입니다." , userName = userInfo.name.toString()) }
    ) {
        ClubJoinCheckDialogTextView(userInfo)
        Row {
            DarkButton(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically),
                buttonText = "거절하기",
                textColor = Color.White,
                radius = 20.dp
            ) {
                onDismiss()
            }
            HorizontalSpacer(value = 15)
            DialogCustomGradientButton(
                modifier = Modifier.weight(1f),
                gradientColors = gradientColorsList,
                buttonText = "수락하기",
                radius = 20.dp
            ) {
            }
        }
    }
}

@Composable
fun ClubJoinCheckDialogTextView(userInfo: UserInfo) {
    Column {
        Text(text = "나이: ${userInfo.age} ")
        Text(text = "가입 구단 수: ${userInfo.clubNum}")
        Text(text = "주 포메이션: ${userInfo.formation}")
        Text(text = "자기 소개: ${userInfo.selfInfo}")
    }
}

@Preview
@Composable
fun ClubJoinCheckDialogPreview() {
    DefaultDialog(
        header = { Header(onDismiss = {}, title = "테스트 입니다.", userName = "으아아" ) }
    ) {
        Text(text = "나이: 3 ")
        Text(text = "가입 구단 수: 3")
        Text(text = "주 포메이션: 3")
        Text(text = "자기 소개: 3")
        Row() {
            DarkButton(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically),
                buttonText = "거절하기",
                textColor = Color.White,
                radius = 20.dp
            ) {
            }
            HorizontalSpacer(value = 15)
            DialogCustomGradientButton(
                modifier = Modifier.weight(1f),
                gradientColors = gradientColorsList,
                buttonText = "수락하기",
                radius = 20.dp
            ) {

            }
        }
    }
}