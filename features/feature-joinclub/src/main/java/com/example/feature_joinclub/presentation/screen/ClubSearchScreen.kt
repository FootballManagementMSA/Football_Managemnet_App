package com.example.feature_joinclub.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.model.ClubInfo
import com.example.feature_joinclub.presentation.ui_component.ClubContent
import com.example.feature_joinclub.presentation.ui_component.ClubItem
import com.example.feature_joinclub.presentation.viewmodel.ClubSearchViewModel
import com.example.ui_component.VerticalSpacer
import com.example.ui_component.buttons.DarkButton
import com.example.ui_component.template.DefaultDialog
import com.example.ui_component.template.Header
import com.example.ui_component.values.hugeFont
import com.example.ui_component.values.mainTheme
import com.example.ui_component.values.tinyFont


@Composable
fun ClubSearchScreen(
    onJoinIconClick: (String) -> Unit,
    onNavigateToRequestJoin: () -> Unit,
    viewModel: ClubSearchViewModel = hiltViewModel(),
    teamList:State<List<ClubInfo>>,
    value: String


) {
    val selectedIndex = remember {
        mutableStateOf(-1)
    }
    val isDialogOpen = remember { mutableStateOf<Boolean>(false) }

    Column(
        Modifier
            .fillMaxSize()
            .background(mainTheme)
            .padding(40.dp)
    ) {
        Box(Modifier.fillMaxWidth()) {
            Icon(
                modifier = Modifier.align(Alignment.CenterEnd),
                imageVector = Icons.Default.Menu,
                contentDescription = "",
                tint = Color.White
            )
        }
        Text(
            text = value,
            fontWeight = FontWeight.Bold,
            fontSize = hugeFont,
            color = Color.White
        )
        Text(
            text = "에 대한 검색결과",
            fontWeight = FontWeight.Bold,
            fontSize = hugeFont,
            color = Color.White
        )
        VerticalSpacer(value = 10)
        Text(
            text = "구단의 이름이나 코드로 검색 할 수 있어요.",
            fontWeight = FontWeight.Bold,
            fontSize = tinyFont,
            color = Color.White
        )
        VerticalSpacer(value = 20)
        LazyColumn(Modifier.fillMaxSize()) {
            itemsIndexed(
                teamList.value,
                key = { _, item -> item.uniqueNum }) { index, club ->
                ClubItem(selectedIndex, index, on = { isDialogOpen.value = true })
                {
                    ClubContent(club = club)
                }
                if (isDialogOpen.value) {
                    checkLogOutDialog(
                        name = "hi",
                        onJoinRequest = { viewModel.clubJoinReqeust(1,"안녕하세요 가입 신청입니다.") }
                        ,
                        navigateToClubPageScreen = {
                            onNavigateToRequestJoin()

                        }) {
                        isDialogOpen.value = false
                    }
                }
            }
        }

    }

}


@Composable
fun checkLogOutDialog(
    name: String,
    navigateToClubPageScreen: () -> Unit,
    onJoinRequest:()->Unit,
    onDismiss: () -> Unit
) {
    DefaultDialog(
        header = { Header(onDismiss = onDismiss, title = "구단 가입 신청입니다.", userName = name) }
    ) {
        VerticalSpacer(value = 10)


        Text(text = "자기 소개 입력")
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("자기 소개 글을 입력해주세요.") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        DarkButton(
            buttonText = "가입 신청",
            textColor = Color.White,
            radius = 20.dp
        ) {
            navigateToClubPageScreen()

        }
    }
}

/*
@Preview
@Composable
fun checkLogOutDialogPreview() {
    checkLogOutDialog("test", {}, {})
}
*/

@Composable
@Preview
fun ClubSearchScreenPreview() {
    val sampleClubList = listOf(
        ClubInfo(
            teamId = 3,
            teamName = "Test",
            totalMemberCnt = 10,
            details = "hi",
            uniqueNum = "bcd",
            emblem = ""
        ),

        ClubInfo(
            teamId = 3,
            teamName = "Test",
            totalMemberCnt = 10,
            details = "hi",
            uniqueNum = "bcd2",
            emblem = ""
        )
    )
    ClubSearchScreen(
        teamList = remember { mutableStateOf(sampleClubList) },
        value = "Test",
        onJoinIconClick = {},
        onNavigateToRequestJoin = {}
    )


}