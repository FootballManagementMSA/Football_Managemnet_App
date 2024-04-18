package com.sejongunivclub.feature_mypage.presentation.screen

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sejongunivclub.core.ResultState.BaseResult
import com.sejongunivclub.core.model.StudentUiModel
import com.sejongunivclub.feature_mypage.presentation.ui_component.ModifyProfileImageView
import com.sejongunivclub.feature_mypage.presentation.ui_component.MyPageModifyInfoView
import com.sejongunivclub.feature_mypage.presentation.viewmodel.MyPageViewModel
import com.sejongunivclub.ui_component.buttons.CustomGradientButton
import com.sejongunivclub.ui_component.values.gradientColorsList
import com.sejongunivclub.ui_component.values.mainTheme

@Composable
fun MyPageModifyScreen(
    navHostController: NavHostController,
    viewModel: MyPageViewModel = hiltViewModel(),
    onNavigateToMyPage: () -> Unit
) {
    val scrollState = rememberScrollState()
    val config = LocalConfiguration.current
    val context = LocalContext.current
    val studentData = viewModel.studentData.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.modifyUserInfoResult.collect {
            when (it) {
                is BaseResult.Success -> {
                    Toast.makeText(
                        context,
                        "정보 수정 성공!",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is BaseResult.Error -> {
                    Toast.makeText(
                        context,
                        "정보 수정 실패: ${it.errorMessage}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
    Column(
        if (isScrollable(config))
            Modifier
        else
            Modifier.verticalScroll(scrollState)
    ) {
        Column(
            if (isScrollable(config))
                Modifier
                    .background(mainTheme)
            else
                Modifier
                    .requiredHeightIn(950.dp)
                    .background(mainTheme)
        ) {
            ModifyProfileImageView(
                modifier = Modifier
                    .requiredHeightIn(min = 200.dp)
                    .weight(2f),
                viewModel.selectedImageUri.collectAsState(),
                onBack = {
                    onNavigateToMyPage()
                },
                onDesroy = {
                    onNavigateToMyPage()
                }
            ) {
                viewModel.updateSelectedImageUri(it)
            }
            MyPageModifyInfoView(
                modifier = Modifier
                    .requiredHeightIn(min = 650.dp)
                    .weight(8f)
                    .fillMaxWidth(),
                studentData = studentData as MutableState<StudentUiModel>
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .requiredHeightIn(100.dp)
                    .align(Alignment.CenterHorizontally)
                    .width(300.dp)
            ) {
                CustomGradientButton(
                    gradientColors = gradientColorsList,
                    cornerRadius = 16.dp,
                    buttonText = "확인",
                    roundedCornerShape = RoundedCornerShape(20.dp)
                ) {
                    viewModel.modifyUserInfo()
                }
            }
        }
    }
}

private fun isScrollable(config: Configuration) = config.screenHeightDp.dp > 950.dp

@Preview
@Composable
fun MyPageModifyScreenPreview() {
    MyPageModifyScreen(navHostController = rememberNavController(), onNavigateToMyPage = {})
}