package com.example.presentation.screen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.core.model.MainSchedule
import com.example.core.model.StudentUiModel
import com.example.presentation.ScheduleDataState
import com.example.presentation.StudentDataState
import com.example.presentation.ui_component.MyInfoView
import com.example.presentation.ui_component.ScheduleView
import com.example.presentation.ui_component.StatusView
import com.example.presentation.viewmodel.MainHomeViewModel
import com.example.ui_component.VerticalSpacer
import com.example.ui_component.values.mainTheme

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    mainHomeViewModel: MainHomeViewModel = hiltViewModel()
) {
    val uiState = mainHomeViewModel.uiState.collectAsState()
    val scheduleUiState = mainHomeViewModel.scheduleUiState.collectAsState()
    var studentData : StudentUiModel?= null
    var scheduleData: List<MainSchedule?> = emptyList()

    LaunchedEffect(Unit){
        mainHomeViewModel.getResponse()
        mainHomeViewModel.getScheduleData()
        mainHomeViewModel.getJoinedClubInfo()
    }

    when (uiState.value) {
        is StudentDataState.Loading -> {
            CircularProgressIndicator()
        }
        is StudentDataState.Success -> {
            studentData = (uiState.value as StudentDataState.Success).data
        }
    }

    when (scheduleUiState.value) {
        is ScheduleDataState.Loading -> {
            CircularProgressIndicator()
        }
        is ScheduleDataState.Success -> {
            scheduleData = (scheduleUiState.value as ScheduleDataState.Success).data
        }
    }
    val config = LocalConfiguration.current
    val scrollState = rememberScrollState()
    Column(
        if (isFixed(config))
            Modifier.background(mainTheme)
        else
            Modifier
                .background(mainTheme)
                .verticalScroll(scrollState)
    ) {
        Column(
            if (isFixed(config))
                Modifier
                    .background(mainTheme)
            else
                Modifier
                    .requiredHeightIn(650.dp)
                    .background(mainTheme)
        ) {
            if (studentData != null) {
                ProfileView(
                    modifier = Modifier
                        .requiredHeightIn(min = 250.dp)
                        .weight(4f),
                    navHostController = navHostController,
                    studentData = studentData
                )
            }
            VerticalSpacer(value = 60)
            ScheduleView(
                Modifier
                    .requiredHeightIn(min = 400.dp)
                    .weight(7f), mutableStateOf(scheduleData)
            ) {
                navHostController.navigate("make_schedule")
            }
        }
    }

}

private fun isFixed(config: Configuration) = config.screenHeightDp.dp > 650.dp

@Composable
private fun ProfileView(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    studentData: StudentUiModel
) {
    Column(modifier) {
        MyInfoView(
            Modifier
                .requiredHeightIn(200.dp)
                .weight(5f), navHostController,
            studentName = studentData.name,
            profileImage = studentData.image
        )
        StatusView(
            Modifier
                .requiredHeightIn(min = 100.dp)
                .weight(2f)
                .heightIn(max = 150.dp),
            studentUiModel = studentData
        )
    }
}


@Composable
@Preview
fun MainHomeScreenPreview() {
    HomeScreen(navHostController = rememberNavController())
}