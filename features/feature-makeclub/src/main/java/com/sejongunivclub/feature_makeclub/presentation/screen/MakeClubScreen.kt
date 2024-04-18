package com.sejongunivclub.feature_makeclub.presentation.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sejongunivclub.feature_makeclub.presentation.ui_component.make_club.ClubDetailsInputField
import com.sejongunivclub.feature_makeclub.presentation.ui_component.make_club.ClubDetailsView
import com.sejongunivclub.feature_makeclub.presentation.ui_component.make_club.ClubIdInfoBox
import com.sejongunivclub.feature_makeclub.presentation.ui_component.make_club.ClubNameInputField
import com.sejongunivclub.feature_makeclub.presentation.ui_component.make_club.ClubNameView
import com.sejongunivclub.feature_makeclub.presentation.ui_component.make_club.MakeClubTopView
import com.sejongunivclub.feature_makeclub.presentation.viewmodel.MakeClubViewModel
import com.sejongunivclub.ui_component.buttons.CustomGradientButton
import com.sejongunivclub.ui_component.values.gradientColorsList
import com.sejongunivclub.ui_component.values.mainTheme

@Composable
fun MakeClubScreen(
    viewModel: MakeClubViewModel = hiltViewModel(),
    onNavigateToEmblemSelect: () -> Unit
) {
    val scrollState = rememberScrollState()
    val clubName = viewModel.clubName.collectAsState("")
    val clubDetails = viewModel.clubDetails.collectAsState("")
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(color = mainTheme)
            .padding(40.dp)
    ) {
        MakeClubTopView() {
            if (clubName.value.isNotEmpty()) {
                onNavigateToEmblemSelect()
            } else {
                Toast.makeText(context, "구단명을 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
        ClubNameView()
        ClubNameInputField(
            state = clubName,
            onValueChange = { viewModel.updateClubName(it) })
        ClubIdInfoBox(text = "중복되는 이름도 신규 생성 가능합니다.")
        ClubDetailsView()
        ClubDetailsInputField(state = clubDetails, onValueChange = { viewModel.updateClubDetails(it) })
        CustomGradientButton(
            gradientColors = gradientColorsList,
            cornerRadius = 16.dp,
            buttonText = "다음",
            roundedCornerShape = RoundedCornerShape(20.dp)
        ) {
            if (clubName.value.isNotEmpty()) {
                onNavigateToEmblemSelect()
            } else {
                Toast.makeText(context, "구단명을 입력해주세요.", Toast.LENGTH_SHORT).show()
            }

        }
    }
}

@Preview
@Composable
fun MakeClubScreenPreview() {
    MakeClubScreen(onNavigateToEmblemSelect = {})
}