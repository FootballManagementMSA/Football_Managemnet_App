package com.sejongunivclub.feature_memberinfo.presentation.ui_component


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidthIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sejongunivclub.ui_component.CircleShapeClickableIcon
import com.sejongunivclub.ui_component.HorizontalSpacer
import com.sejongunivclub.ui_component.R
import com.sejongunivclub.ui_component.VerticalSpacer
import com.sejongunivclub.ui_component.values.bigFont
import com.sejongunivclub.ui_component.values.horizontalGradation
import com.sejongunivclub.ui_component.values.largeIcon
import com.sejongunivclub.ui_component.values.mainTheme
import com.sejongunivclub.ui_component.values.profileInfoButton
import com.sejongunivclub.ui_component.values.tinyFont
import com.sejongunivclub.ui_component.values.veryBigFont

@Composable
fun MemberInfoView(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(20.dp)
            .fillMaxWidth()
    ) {
        Row(
            Modifier,
            verticalAlignment = Alignment.Bottom
        ) {
            Info(
                Modifier
                    .requiredWidthIn(150.dp)
                    .weight(2f)
                    .fillMaxSize(),
            )
            ProfileImage(
                Modifier
                    .weight(3f)
            )
        }
    }

}

@Composable
fun Info(modifier: Modifier = Modifier) {
    Column(modifier) {
        CircleShapeClickableIcon(
            size = largeIcon,
            background = profileInfoButton,
            icon = R.drawable.league_icon
        ) {

        }
        Spacer(modifier = Modifier.height(10.dp))
        CircleShapeClickableIcon(
            size = largeIcon,
            background = profileInfoButton,
            icon = R.drawable.league_icon
        ) {

        }
        Spacer(modifier = Modifier.weight(1f))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(largeIcon),
                painter = painterResource(id = R.drawable.cloth_icon),
                contentDescription = "cloth"
            )
            HorizontalSpacer(value = 10)
            Text(text = "07", fontSize = bigFont, style = TextStyle(color = Color.White))
        }
        VerticalSpacer(value = 10)
        Row(
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = "홍길동",
                fontSize = veryBigFont,
                style = TextStyle(color = Color.White)
            )
            HorizontalSpacer(value = 10)
            Text(
                text = "님의 페이지",
                fontSize = tinyFont,
                style = TextStyle(color = Color.White)
            )
        }
    }
}

@Composable
fun ProfileImage(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier
            .wrapContentSize()
            .border(width = 3.dp, brush = horizontalGradation, shape = CircleShape),
        painter = painterResource(id = R.drawable.default_profile_image),
        contentDescription = "profileImage"
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
            )
            ProfileImage(
                Modifier
                    .weight(1f)
                    .fillMaxSize(),
            )
        }
    }
}

@Composable
@Preview
fun ProfileInfoPreview() {
    Info(
        modifier = Modifier
            .height(300.dp)
            .background(mainTheme)
    )
}

@Composable
@Preview
fun ProfileImagePreview() {
    ProfileImage(
        modifier = Modifier
            .height(300.dp)
            .width(300.dp)
            .background(mainTheme)
    )
}
