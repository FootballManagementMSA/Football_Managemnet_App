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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.model.Club
import com.example.core.model.ClubInfo
import com.example.feature_joinclub.presentation.ui_component.ClubContent
import com.example.feature_joinclub.presentation.ui_component.ClubItem
import com.example.feature_joinclub.presentation.ui_component.ClubSearchView
import com.example.ui_component.template.DefaultBottomSheet
import com.example.ui_component.template.DefaultListView
import com.example.ui_component.values.mainTheme

@Composable
fun JoinClubScreen(
    onSearchIconClick: (String) -> Unit,
    onNavigateToMakeClub: () -> Unit,
    onNavigateToClubSearch: () -> Unit,
    getJoinedClub: () -> Unit
) {
    val showSheet = remember {
        mutableStateOf(true)
    }
    val teamList = remember {
        mutableStateOf(
            dummyClub()
        )
    }
    LaunchedEffect(Unit) {
        getJoinedClub()
    }
    if (showSheet.value) {
        DefaultBottomSheet(onDismiss = { showSheet.value = false }) {
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
                        teamList.value.data,
                        key = { _, item -> item.uniqueNum }) { index, club ->
                        ClubItem(selectedIndex, index,{}) { ClubContent(club = club) }
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
    JoinClubScreen(onSearchIconClick = {}, onNavigateToMakeClub = {}, onNavigateToClubSearch = {}) {}
}

fun dummyClub() = Club(
    status = 1,
    message = "message",
    data = listOf(
        ClubInfo(teamId = 3, teamName = "구단명", totalMemberCnt = 20, details = "", uniqueNum = "3da", emblem = "emblem_uri"),
        ClubInfo(teamId = 3, teamName = "구단명2", totalMemberCnt = 20, details = "", uniqueNum = "3db", emblem = "emblem_uri"),
        ClubInfo(teamId = 3, teamName = "구단명3", totalMemberCnt = 20, details = "", uniqueNum = "3dc", emblem = "emblem_uri"),
        ClubInfo(teamId = 3, teamName = "구단명4", totalMemberCnt = 20, details = "", uniqueNum = "3de", emblem = "emblem_uri"),
    )
)

