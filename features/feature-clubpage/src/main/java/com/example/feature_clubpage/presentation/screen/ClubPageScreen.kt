package com.example.feature_clubpage.presentation.screen


import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.core.model.ClubMemberUiModel
import com.example.core.model.ScheduleUiModel
import com.example.feature_clubpage.presentation.ui_component.ClubInfoView
import com.example.feature_clubpage.presentation.ui_component.ScheduleView_Sample
import com.example.feature_clubpage.presentation.ui_component.generateDummyData
import com.example.feature_clubpage.presentation.ui_component.generateDummyData1
import com.example.ui_component.values.mainTheme

@Composable
fun ClubPageScreen(
    navHostController: NavHostController,
    getCreatedAt: () -> Unit,
    createdAt: State<String>,
    getTeamSizeOfUsers: () -> Unit,
    teamSize: State<String>,
    getTeamUniqueNum: () -> Unit,
    uniqueNum: State<String>,
    getTeamName: () -> Unit,
    teamName: State<String>,
    getTeamEmblem: () -> Unit,
    emblem: State<String>
) {
    val config = LocalConfiguration.current
    val currentSchedule = remember {
        mutableStateOf(
            ScheduleUiModel(
                schedule = generateDummyData(5)
            ).schedule
        )
    }
    val currentClubMember = remember {
        mutableStateOf(
            ClubMemberUiModel(
                clubMember = generateDummyData1(5)
            ).clubMember
        )
    }

    val scrollState = rememberScrollState()
    LaunchedEffect(key1 = true) {
        getCreatedAt()
        getTeamSizeOfUsers()
        getTeamUniqueNum()
        getTeamName()
        getTeamEmblem()
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
                    .requiredHeightIn(800.dp)
                    .background(mainTheme)
        ) {
            ProfileView(
                modifier = Modifier
                    .weight(3f),
                createdAt = createdAt,
                sizeOfUsers = teamSize,
                uniqueNum = uniqueNum,
                teamName = teamName,
                emblem = emblem
            )
            ScheduleView_Sample(
                navHostController = navHostController,
                Modifier
                    .weight(7f), currentSchedule, currentClubMember
            )
        }
    }

}

private fun isScrollable(config: Configuration) = config.screenHeightDp.dp > 800.dp

@Composable
private fun ProfileView(
    modifier: Modifier = Modifier,
    createdAt: State<String>,
    sizeOfUsers: State<String>,
    uniqueNum: State<String>,
    teamName: State<String>,
    emblem: State<String>
) {
    Column(modifier) {
        ClubInfoView(
            Modifier
                .requiredHeightIn(200.dp)
                .weight(5f),
            createdAt,
            sizeOfUsers,
            uniqueNum,
            teamName,
            emblem
        )

    }
}


@Composable
@Preview
fun ProfileScreenPreview() {
    ProfileView(
        createdAt = mutableStateOf(""),
        sizeOfUsers = mutableStateOf(""),
        uniqueNum = mutableStateOf(""),
        teamName = mutableStateOf(""),
        emblem = mutableStateOf("")
    )
}

@Composable
@Preview
fun ClubPageScreenPreview() {
    ClubPageScreen(
        rememberNavController(),
        {},
        mutableStateOf(""),
        {},
        mutableStateOf(""),
        {},
        mutableStateOf(""),
        {},
        mutableStateOf(""),
        {},
        mutableStateOf("")
    )
}