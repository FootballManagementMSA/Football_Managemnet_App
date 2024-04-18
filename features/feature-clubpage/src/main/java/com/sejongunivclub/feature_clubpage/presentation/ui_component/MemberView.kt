package com.sejongunivclub.feature_clubpage.presentation.ui_component

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sejongunivclub.core.model.ClubMember
import com.sejongunivclub.ui_component.R
import com.sejongunivclub.ui_component.buttons.CharacteristicButton
import com.sejongunivclub.ui_component.values.mainTheme
import com.sejongunivclub.ui_component.values.pink
import com.sejongunivclub.ui_component.values.purple
import com.sejongunivclub.ui_component.values.smallFont
import com.sejongunivclub.ui_component.values.tinyFont
import com.sejongunivclub.ui_component.values.tinyIcon

@Composable
fun ClubMemberItem(modifier: Modifier = Modifier, clubMember: ClubMember) {
    Column(
        modifier = modifier
            .padding(5.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
        ) {
            RandomProfileImage(
                modifier = Modifier
                    .weight(3f)
                    .align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.weight(1f))

            MemberInfo(
                modifier = Modifier
                    .weight(7f)
                    .fillMaxSize(), clubMember
            )

            Icon(
                painter = painterResource(id = R.drawable.arrow_icon),
                contentDescription = null,
                modifier = Modifier
                    .size(tinyIcon)
                    .align(Alignment.CenterVertically),
                tint = Color.White

            )



        }
    }
}

@Composable
fun MemberInfo(modifier: Modifier = Modifier, clubMember: ClubMember) {
    Column(modifier) {

        Spacer(modifier = Modifier.weight(0.01f))

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){

            Text(
                text = clubMember.name,
                fontSize = smallFont,
                style = TextStyle(color = Color.White)
            )
            Text(
                modifier = Modifier.padding(top = 3.dp),
                text = " 님",
                fontSize = tinyFont,
                style = TextStyle(color = Color.White)
            )
            Spacer(Modifier.size(4.dp))

            CharacteristicButton(onClick = {}, buttonColor = pink, textColor =Color.White, text = "NF" )
            Spacer(Modifier.size(1.dp))

            CharacteristicButton(onClick = {}, buttonColor = purple, textColor =Color.White, text = "득점왕" )

        }

        Spacer(modifier = Modifier.weight(0.008f))

        Text(
            text = "나이 : " + clubMember.age + "세",
            fontSize = tinyFont,
            style = TextStyle(color = Color.White)
        )

        Text(
            text = "가입 구단 수: " + clubMember.number + "개",
            fontSize = tinyFont,
            style = TextStyle(color = Color.White)
        )



        Spacer(modifier = Modifier.weight(0.005f))


    }
}
@Composable
fun RandomProfileImage(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier
            .wrapContentSize()
            .border(width = 3.dp, color = mainTheme, shape = CircleShape),
        painter = painterResource(id = R.drawable.only_male_image),
        contentDescription = "basic_club_image"
    )
}

@Composable
@Preview
fun ScheduleItemPreview3() {

    ClubMemberItem(
        modifier = Modifier
            .height(150.dp)
            .padding(15.dp),
        clubMember = generateDummyData1(1)[0]
    )
}