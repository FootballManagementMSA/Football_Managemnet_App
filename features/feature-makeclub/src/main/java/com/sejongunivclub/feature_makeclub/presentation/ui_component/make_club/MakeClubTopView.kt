package com.sejongunivclub.feature_makeclub.presentation.ui_component.make_club

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.sejongunivclub.ui_component.R
import com.sejongunivclub.ui_component.VerticalSpacer
import com.sejongunivclub.ui_component.values.largeIcon
@Composable
fun MakeClubTopView(onClick: () -> Unit) {
    Column {
        NextArrowImageView(onClick)
        VerticalSpacer(value = 48)
        TermsAgreeText()
        VerticalSpacer(value = 70)
    }
}

@Composable
fun NextArrowImageView(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Icon(
            painter = painterResource(id = R.drawable.next_icon),
            contentDescription = null,
            modifier = Modifier
                .size(largeIcon)
                .align(Alignment.TopEnd)
                .clickable { onClick() },
            tint = Color.White
        )
    }
}


@Composable
fun TermsAgreeText() {
    Text(
        text = "구단의 이름을\n\n입력해 주세요.",
        fontSize = 30.sp,
        color = Color.White,
        fontWeight = FontWeight.Bold
    )
}

@Composable
@Preview
fun TopScreenPreview() {
    MakeClubTopView({})
}