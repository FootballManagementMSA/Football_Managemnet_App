package com.example.feature_join.presentation.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.core.ResultState.JoinResult
import com.example.core.ResultState.LoginResult
import com.example.feature_join.presentation.ui_component.IconView
import com.example.feature_join.presentation.viewmodel.JoinViewModel
import com.example.ui_component.InputView
import com.example.ui_component.buttons.CustomGradientButton
import com.example.ui_component.buttons.WhiteButton

val LocalLoginScreenPreviewMode: ProvidableCompositionLocal<Boolean> = compositionLocalOf { false }

@Composable
fun StudentVerificationScreen(
    navHostController: NavHostController,
    onNavigateToJoinScreen: () -> Unit

) {

    val viewModel: JoinViewModel = hiltViewModel()
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val JoinResult by viewModel.JoinResult.collectAsState(initial = LoginResult.UnInitialize)

    LaunchedEffect(Unit) {
        viewModel.JoinResult.collect { loginResult ->
            when (JoinResult) {
                is LoginResult.Success -> {
                    navHostController.navigate("HOME") {
                        popUpTo("LOGIN") {
                            inclusive = true
                        }
                    }

                }

                is LoginResult.Error -> {
                    Toast.makeText(
                        context,
                        "로그인 실패: ${(loginResult as LoginResult.Error).errorMessage}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else -> {}
            }
        }

    }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .background(color = Color(0xFF232A39))
                .padding(40.dp)
        ) {
            IconView()

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "재학생 인증이\n\n필요합니다.",
                fontSize = 30.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.weight(0.5f))

            InputView(
                state = viewModel.userId,
                label = " 학번",
                placeholder = "학번을 입력해 주세요.",
                onValueChange = { viewModel.updateUserId(it) })

            Spacer(modifier = Modifier.weight(0.5f))

            InputView(
                state = viewModel.userPassword,
                label = "비밀번호",
                placeholder = "비밀번호를 입력해 주세요",
                onValueChange = { viewModel.updateUserPassword(it) })

            Spacer(modifier = Modifier.weight(3f))


            Text(
                text = "학교 홈페이지 학번 비밀번호를 입력해주세요.",
                color = Color(0xFF857FEB),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 8.dp),
                fontSize = 10.sp
            )

            CustomGradientButton(
                gradientColors = listOf(Color(0xFFEE6DE7), Color(0xFF857FEB)),
                cornerRadius = 16.dp,
                buttonText = "재학생 인증하기",
                roundedCornerShape = RoundedCornerShape(20.dp)
            ) {
                onNavigateToJoinScreen()

            }

            Spacer(modifier = Modifier.weight(1f))


        }

    }

}
