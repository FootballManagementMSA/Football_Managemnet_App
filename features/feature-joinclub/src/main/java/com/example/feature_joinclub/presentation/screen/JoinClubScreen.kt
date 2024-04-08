package com.example.feature_joinclub.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.model.UserTeamInfoModel
import com.example.feature_joinclub.presentation.ui_component.ClubItem
import com.example.feature_joinclub.presentation.ui_component.ClubSearchView
import com.example.feature_joinclub.presentation.ui_component.JoinedClubContent
import com.example.ui_component.template.DefaultBottomSheet
import com.example.ui_component.template.DefaultListView
import com.example.ui_component.values.bottomSheetColor
import com.example.ui_component.values.mainTheme

@Composable
fun JoinClubScreen(
    onSearchIconClick: (String) -> Unit,
    onNavigateToMakeClub: () -> Unit,
    onNavigateToClubSearch: () -> Unit,
    getJoinedClub: () -> Unit,
    joinedTeamList: State<List<UserTeamInfoModel>>,
    onNavigateToClubPage: () -> Unit,
    saveUniqueNum: (String) -> Unit,
    saveRole: (String) -> Unit,
    saveIntroduce: (String) -> Unit,
    saveTeamName: (String) -> Unit,
    saveTeamEmblem: (String) -> Unit,
    saveCreatedAt: (String) -> Unit,
    saveSizeOfUsers: (Int) -> Unit,
    saveTeamId: (String) -> Unit
) {
    val showSheet = remember {
        mutableStateOf(true)
    }
    LaunchedEffect(Unit) {
        getJoinedClub()
    }
    if (showSheet.value) {
        DefaultBottomSheet(
            onDismiss = { showSheet.value = false },
            containerColor = bottomSheetColor
        ) {
            val selectedIndex = remember {
                mutableStateOf(-1)
            }
            DefaultListView(
                modifier = Modifier
                    .padding(20.dp)
                    .selectableGroup(),
                themeColor = Color.Black,
                listName = "현재 가입된 구단 목록",
                listIcon = Icons.Default.Person,
                buttonName = "전체보기",
                showButton = true,
                onClick = { },
                listContent = {
                    itemsIndexed(
                        joinedTeamList.value,
                        key = { _, item -> item.unique_num }) { index, club ->
                        ClubItem(selectedIndex, index, {}) {
                            JoinedClubContent(
                                club = club,
                                onNavigateToClubPage,
                                saveUniqueNum,
                                saveRole,
                                saveIntroduce,
                                saveTeamName,
                                saveTeamEmblem,
                                saveCreatedAt,
                                saveSizeOfUsers,
                                saveTeamId
                            )
                        }
                    }
                }
            )
        }
    }
    Column(
        Modifier
            .fillMaxSize()
            .background(mainTheme)
            .padding(40.dp)
    ) {
        ClubSearchView(
            Modifier
                .requiredHeightIn(min = 300.dp)
                .weight(2f),
            showSheet = { showSheet.value = !showSheet.value },
            onSearchIconClick = {
                onSearchIconClick(it)
                onNavigateToClubSearch()
            },
            onNavigateToMakeClub = { onNavigateToMakeClub() }
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(3f)
        )
    }
}

@Composable
@Preview
fun JoinClubScreenPreview() {
    val dummyData = listOf(
        UserTeamInfoModel(
            role = "Member",
            introduce = "preview",
            teamName = "preview",
            unique_num = "CW123",
            teamEmblem = "",
            createdAt = "",
            sizeOfUsers = 15
        ),
    )
    JoinClubScreen(
        onSearchIconClick = {},
        onNavigateToMakeClub = {},
        onNavigateToClubSearch = {},
        getJoinedClub = {},
        joinedTeamList = remember { mutableStateOf(dummyData) },
        onNavigateToClubPage = {},
        saveUniqueNum = {},
        saveSizeOfUsers = {},
        saveCreatedAt = {},
        saveTeamEmblem = {},
        saveTeamName = {},
        saveIntroduce = {},
        saveRole = {},
        saveTeamId = {}
    )
}
