package com.example.feature_mypage.presentation.ui_component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ui_component.HorizontalSpacer
import com.example.ui_component.R
import com.example.ui_component.VerticalSpacer
import com.example.ui_component.values.firstGradientColor
import com.example.ui_component.values.largeIcon
import com.example.ui_component.values.subTheme

@Composable
fun MyPageCardView(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onLogout: () -> Unit,
    onSignOut: () -> Unit
) {
    Column(modifier = modifier) {
        MyPageCardItem(radius = 8.dp) {
            Image(
                painter = painterResource(id = R.drawable.check_icon),
                contentDescription = null,
                modifier = Modifier
                    .size(largeIcon)
                    .align(Alignment.CenterHorizontally)
                    .padding(start = 15.dp)
            )
            HorizontalSpacer(value = 10)
            Text(
                text = "프로필 변경",
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.arrow_right_icon),
                contentDescription = null,
                modifier = Modifier
                    .size(largeIcon)
                    .align(Alignment.CenterHorizontally)
                    .padding(end = 15.dp)
                    .clickable { onClick() }
            )
        }
        VerticalSpacer(value = 20)
        MyPageCardItem(radius = 8.dp) {
            Image(
                painter = painterResource(id = R.drawable.check_icon),
                contentDescription = null,
                modifier = Modifier
                    .size(largeIcon)
                    .align(Alignment.CenterHorizontally)
                    .padding(start = 15.dp)
            )
            HorizontalSpacer(value = 10)
            Text(
                text = "로그아웃",
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.arrow_right_icon),
                contentDescription = null,
                modifier = Modifier
                    .size(largeIcon)
                    .align(Alignment.CenterHorizontally)
                    .padding(end = 15.dp)
                    .clickable { onLogout() }
            )

        }
        VerticalSpacer(value = 20)
        MyPageCardItem(modifier = Modifier.clickable { onSignOut() },radius = 8.dp) {
            Image(
                painter = painterResource(id = R.drawable.check_icon),
                contentDescription = null,
                modifier = Modifier
                    .size(largeIcon)
                    .align(Alignment.CenterHorizontally)
                    .padding(start = 15.dp)
            )
            HorizontalSpacer(value = 10)
            Text(
                text = "회원탈퇴",
                color = firstGradientColor,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
fun MyPageCardItem(
    modifier: Modifier = Modifier,
    radius: Dp,
    color: Color = subTheme,
    content: @Composable () -> Unit
) {
    val config = LocalConfiguration.current
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(radius))
            .background(color)
            .fillMaxWidth()
            .heightIn(min = 60.dp, max = 150.dp)
            .height(config.screenHeightDp.dp / 10)
    ) {
        content()
    }
}

@Preview
@Composable
fun MyPageCardViewPreview() {
    MyPageCardView(onClick = {}, onLogout = {}) {}
}