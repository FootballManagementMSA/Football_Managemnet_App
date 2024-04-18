package com.sejongunivclub.feature_join.presentation.screen

import GenderView
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sejongunivclub.core.ResultState.JoinResult
import com.sejongunivclub.feature_join.presentation.ui_component.InputView_OnlyNum
import com.sejongunivclub.feature_join.presentation.viewmodel.JoinViewModel
import com.sejongunivclub.ui_component.buttons.CustomGradientButton_1
import com.sejongunivclub.ui_component.values.mainTheme

@Composable
fun JoinScreen(
    navHostController: NavHostController,
    viewModel: JoinViewModel = hiltViewModel(),
    onNavigateToProfileSettingScreen: () -> Unit,
) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    var isGenderSelected by remember { mutableStateOf(false) }
    var isAgeEntered by remember { mutableStateOf(false) }
    var isHeightEntered by remember { mutableStateOf(false) }



    LaunchedEffect(Unit) {
        viewModel.JoinResult.collect { JoinResult ->
            when (JoinResult) {
                is JoinResult.Success -> {
                    Toast.makeText(
                        context,
                        "성공",
                        Toast.LENGTH_SHORT
                    ).show()


                }

                is JoinResult.Error -> {
                    Toast.makeText(
                        context,
                        "회원가입 실패: ${(JoinResult as JoinResult.Error).errorMessage}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else -> {}
            }
        }

    }







    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(color = mainTheme)
            .padding(40.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(40.dp))


        Text(
            text = "성별과 나이,\n\n키를 알려주세요!!",
            fontSize = 30.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(40.dp))

        GenderView(onGenderSelected = { gender ->
            viewModel.updateUserGender(gender)
            isGenderSelected = true

        })
        Spacer(modifier = Modifier.height(10.dp))


        InputView_OnlyNum(
            state = viewModel.userAge,
            label = "나이(출생년도)",
            placeholder = "나이를 입력해주세요.",
            onValueChanges = {
                viewModel.updateUserAge(it)
                isAgeEntered = it.isNotEmpty()
            })


        Spacer(modifier = Modifier.height(10.dp))
        InputView_OnlyNum(
            state = viewModel.userHeight,
            label = "키(cm)",
            placeholder = "키를 입력해주세요.",
            onValueChanges = {
                viewModel.updateUserHeight(it)
                isHeightEntered = it.isNotEmpty()

            })


        Spacer(modifier = Modifier.height(20.dp))

        CustomGradientButton_1(
            gradientColors = listOf(Color(0xFFEE6DE7), Color(0xFF857FEB)),
            cornerRadius = 16.dp,
            buttonText = "시작하기",
            roundedCornerShape = RoundedCornerShape(20.dp)

        ) {
            if (isGenderSelected && isAgeEntered && isHeightEntered) {
                onNavigateToProfileSettingScreen()
            } else {
                val message = when {
                    !isGenderSelected -> "성별을 선택해주세요."
                    !isAgeEntered -> "나이(출생년도)를 입력해주세요."
                    !isHeightEntered-> "키(cm)를 입력해주세요."
                    else -> {"입력이 완료되지 않았습니다."}
                }
                Toast.makeText(
                    context,
                    message,
                    Toast.LENGTH_SHORT
                ).show()

            }
        }

    }
}

