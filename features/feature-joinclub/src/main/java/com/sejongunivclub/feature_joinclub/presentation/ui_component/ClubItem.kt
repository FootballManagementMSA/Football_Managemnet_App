package com.sejongunivclub.feature_joinclub.presentation.ui_component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sejongunivclub.core.model.ClubInfo
import com.sejongunivclub.core.model.UserTeamInfoModel
import com.sejongunivclub.ui_component.HorizontalSpacer
import com.sejongunivclub.ui_component.R
import com.sejongunivclub.ui_component.VerticalSpacer
import com.sejongunivclub.ui_component.template.DefaultItem
import com.sejongunivclub.ui_component.values.horizontalGradation
import com.sejongunivclub.ui_component.values.tinyFont
import com.sejongunivclub.ui_component.values.veryBigFont

@Composable
fun ClubItem(
    selectedIndex: MutableState<Int>,
    index: Int,
    on: () -> Unit,
    content: @Composable () -> Unit
) {
    DefaultItem(
        modifier = Modifier
            .padding(bottom = 12.dp)
            .selectable(
                selected = selectedIndex.value == index,
                onClick =
                {

                    if (selectedIndex.value == index) {
                        selectedIndex.value = -1
                    } else {
                        selectedIndex.value = index
                        on()
                    }

                }

            )
            .border(
                width = 2.dp,
                brush = if (selectedIndex.value == index) horizontalGradation else Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFFFFFF),
                        Color(0xFFFFFFFF)
                    )
                ),
                shape = RoundedCornerShape(8.dp)
            ),
        radius = 8.dp,
        color = Color.White,
        content = content
    )
}

@Composable
fun ClubContent(club: ClubInfo) {
    Row(
        Modifier
            .padding(20.dp)
            .fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(Modifier) {
            Text(text = club.teamName ?: "", fontWeight = FontWeight.Bold, fontSize = veryBigFont)
            VerticalSpacer(value = 10)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.Person, contentDescription = "")
                HorizontalSpacer(value = 5)
                Text(text = "${club.totalMemberCnt} 명", fontSize = tinyFont)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.Person, contentDescription = "")
                HorizontalSpacer(value = 5)
                Text(text = club.details ?: "", fontSize = tinyFont)
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = club.emblem,
                contentDescription = "profile image",
                error = painterResource(id = R.drawable.default_profile_image),
                placeholder = painterResource(
                    id = R.drawable.default_profile_image
                ),
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Icon(
                modifier = Modifier,
                imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                contentDescription = "Icon"
            )
        }
    }
}

@Composable
fun JoinedClubContent(
    club: UserTeamInfoModel,
    onNavigateToClubPage: () -> Unit,
    saveUniqueNum: (String) -> Unit,
    saveRole: (String) -> Unit,
    saveIntroduce: (String) -> Unit,
    saveTeamName: (String) -> Unit,
    saveTeamEmblem: (String) -> Unit,
    saveCreatedAt: (String) -> Unit,
    saveSizeOfUsers: (Int) -> Unit,
    saveTeamId: (String) ->Unit
) {
    Row(
        Modifier
            .padding(20.dp)
            .fillMaxSize()
            .clickable {
                saveUniqueNum(club.unique_num)
                saveRole(club.role)
                saveIntroduce(club.introduce)
                saveTeamName(club.teamName)
                saveTeamEmblem(club.teamEmblem)
                saveCreatedAt(club.createdAt)
                saveSizeOfUsers(club.sizeOfUsers)
                saveTeamId(club.unique_num)
                onNavigateToClubPage()
            },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(Modifier) {
            Text(text = club.teamName ?: "", fontWeight = FontWeight.Bold, fontSize = veryBigFont)
            VerticalSpacer(value = 10)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.Person, contentDescription = "")
                HorizontalSpacer(value = 5)
                Text(text = "${club.sizeOfUsers} 명", fontSize = tinyFont)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.Star, contentDescription = "")
                HorizontalSpacer(value = 5)
                Text(text = club.introduce ?: "", fontSize = tinyFont)
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = club.teamEmblem,
                contentDescription = "profile image",
                error = painterResource(id = R.drawable.default_profile_image),
                placeholder = painterResource(
                    id = R.drawable.default_profile_image
                ),
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Icon(
                modifier = Modifier,
                imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                contentDescription = "Icon"
            )
        }
    }
}

@Composable
@Preview
fun ClubItemPreview() {
    val selectedIndex =
        remember { mutableStateOf(1) }

    ClubItem(selectedIndex = selectedIndex, index = 1, {}) {
        ClubContent(club = ClubInfo(3, "teamname", 20, "unique", "", ""))
    }


}