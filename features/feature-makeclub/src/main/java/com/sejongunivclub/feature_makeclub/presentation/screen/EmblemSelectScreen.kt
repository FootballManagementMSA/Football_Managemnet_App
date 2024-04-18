package com.sejongunivclub.feature_makeclub.presentation.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sejongunivclub.core.ResultState.MakeClubResult
import com.sejongunivclub.feature_makeclub.presentation.ui_component.emblem.EmblemInfoBox
import com.sejongunivclub.feature_makeclub.presentation.ui_component.emblem.EmblemSelectIconView
import com.sejongunivclub.feature_makeclub.presentation.ui_component.emblem.EmblemSelectTopView
import com.sejongunivclub.feature_makeclub.presentation.viewmodel.MakeClubViewModel
import com.sejongunivclub.ui_component.VerticalSpacer
import com.sejongunivclub.ui_component.buttons.WhiteButton
import com.sejongunivclub.ui_component.values.lastGradientColor
import com.sejongunivclub.ui_component.values.mainTheme

@Composable
fun EmblemSelectScreen(
    viewModel: MakeClubViewModel = hiltViewModel(),
    onNavigateToCompleteClubMake: () -> Unit,
    onNavigateToMakeClub: () -> Unit
) {
    val scrollState = rememberScrollState()
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    if (state is MakeClubResult.Success) {
        LaunchedEffect(key1 = state) {
            viewModel.handleSuccess()
            onNavigateToCompleteClubMake()
        }
    }

    when (state) {
        is MakeClubResult.Error -> {
            Toast.makeText(
                context,
                "구단 생성 실패: ${(state as MakeClubResult.Error).errorMessage}",
                Toast.LENGTH_SHORT
            ).show()
        }
        is MakeClubResult.Initial -> {

        }

        else -> {}
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .background(color = mainTheme)
                .padding(40.dp)
        ) {
            EmblemSelectTopView() {
                onNavigateToMakeClub()
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                EmblemSelectIconView(viewModel.selectedImageUri.collectAsState()) {
                    viewModel.updateSelectedImageUri(it)
                }
            }
            VerticalSpacer(value = 60)
            EmblemInfoBox(text = "규격에 맞는 엠블럼 사진을 업로드 해주세요.")
            VerticalSpacer(value = 40)
            EmblemInfoBox(text = "건너뛸 시,학교 로고가 들어갑니다.")
            VerticalSpacer(value = 30)
            WhiteButton(
                buttonText = "건너뛰기",
                textColor = lastGradientColor,
                roundedCornerShape = RoundedCornerShape(20.dp)
            ) {
                viewModel.sendClubInfoData(context)
            }
        }
        if (state is MakeClubResult.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }

}

@Preview
@Composable
fun EmblemSelectScreenPrevew() {
    EmblemSelectScreen(onNavigateToCompleteClubMake = {}, onNavigateToMakeClub = {})
}