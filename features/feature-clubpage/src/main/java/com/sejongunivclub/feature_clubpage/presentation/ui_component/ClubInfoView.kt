package com.sejongunivclub.feature_clubpage.presentation.ui_component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sejongunivclub.ui_component.CircleShapeClickableIcon
import com.sejongunivclub.ui_component.R
import com.sejongunivclub.ui_component.values.grayText2
import com.sejongunivclub.ui_component.values.horizontalGradation
import com.sejongunivclub.ui_component.values.largeIcon
import com.sejongunivclub.ui_component.values.mainTheme
import com.sejongunivclub.ui_component.values.tinyFont
import com.sejongunivclub.ui_component.values.veryBigFont

@Composable
fun ClubInfoView(
    modifier: Modifier = Modifier,
    createdAt: State<String>,
    sizeOfUsers: State<String>,
    uniqueNum: State<String>,
    teamName: State<String>,
    emblem: State<String>
) {

    Column(
        modifier = modifier
            .padding(20.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(), // Make sure the Row takes only the height it needs
        ) {
            Info(
                modifier = Modifier
                    .weight(2.5f)
                    .fillMaxSize(),
                createdAt,
                sizeOfUsers,
                uniqueNum,
                teamName
            )
            ProfileImage(
                modifier = Modifier
                    .weight(1.5f)
                    .align(Alignment.CenterVertically),
                emblem
            )
        }
    }
}

@Composable
fun Info(
    modifier: Modifier = Modifier,
    createdAt: State<String>,
    sizeOfUsers: State<String>,
    uniqueNum: State<String>,
    teamName: State<String>
) {
    Column(modifier) {

        Spacer(modifier = Modifier.weight(0.01f))

        Text(
            text = teamName.value,
            fontSize = veryBigFont,
            style = TextStyle(color = Color.White)
        )
        Spacer(modifier = Modifier.weight(0.0005f))

        Text(
            text = "코드 : ${uniqueNum.value}",
            fontSize = tinyFont,
            style = TextStyle(color = Color.White)
        )
        Spacer(modifier = Modifier.weight(0.01f))




        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            CircleShapeClickableIcon(
                size = largeIcon,
                background = mainTheme,
                icon = R.drawable.people_icon
            ) {

            }
            Text(
                text = sizeOfUsers.value,
                fontSize = tinyFont,
                style = TextStyle(grayText2)
            )
        }


        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically)
        {
            CircleShapeClickableIcon(
                size = largeIcon,
                background = mainTheme,
                icon = R.drawable.opening_date_icon
            ) {

            }
            Text(
                text = createdAt.value,
                fontSize = tinyFont,
                style = TextStyle(grayText2)
            )
        }
        Spacer(modifier = Modifier.weight(0.005f))


    }
}

@Composable
fun ProfileImage(modifier: Modifier = Modifier, emblem: State<String>) {
    AsyncImage(
        model = emblem.value,
        contentDescription = "profile image",
        error = painterResource(id = R.drawable.basic_club_image),
        placeholder = painterResource(
            id = R.drawable.basic_club_image
        ),
        modifier = Modifier
            .size(130.dp)
            .clip(CircleShape)
            .border(width = 3.dp, brush = horizontalGradation, shape = CircleShape),
        contentScale = ContentScale.Crop
    )
}


@Composable
@Preview
fun ProfileViewPreview() {
    Column(
        modifier = Modifier
            .background(mainTheme)
            .height(
                250.dp
            )
            .fillMaxWidth()
    ) {
        Row(Modifier) {
            Info(
                Modifier
                    .weight(1f)
                    .fillMaxSize(),
                createdAt = mutableStateOf(""),
                sizeOfUsers = mutableStateOf(""),
                uniqueNum = mutableStateOf(""),
                teamName = mutableStateOf("")
            )
            ProfileImage(
                Modifier
                    .weight(1f)
                    .fillMaxSize(),
                emblem = mutableStateOf("")
            )
        }
    }
}