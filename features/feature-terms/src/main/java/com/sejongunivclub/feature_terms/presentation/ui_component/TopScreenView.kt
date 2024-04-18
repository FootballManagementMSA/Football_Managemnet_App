package com.sejongunivclub.feature_terms.presentation.ui_component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
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
import com.sejongunivclub.feature_terms.R
import com.sejongunivclub.ui_component.HorizontalSpacer
import com.sejongunivclub.ui_component.VerticalSpacer
import com.sejongunivclub.ui_component.values.largeIcon
import com.sejongunivclub.ui_component.values.smallFont

@Composable
fun TopScreenView() {
    Column {
        BackArrowImageView()
        VerticalSpacer(value = 48)
        AppNameInfo()
        VerticalSpacer(value = 20)
        TermsAgreeText()
        VerticalSpacer(value = 70)
    }
}

@Composable
fun BackArrowImageView() {
    Column {
        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.back_icon),
                contentDescription = null,
                modifier = Modifier
                    .size(largeIcon)
                    .align(Alignment.TopStart),
                tint = Color.White
            )
        }
    }
}

@Composable
fun AppNameInfo(modifier: Modifier = Modifier) {
    Row {
        Icon(
            modifier = modifier.size(largeIcon) ,
            imageVector = Icons.Default.Info,
            contentDescription = "NavIcon",
            tint = Color.White
        )
        HorizontalSpacer(value = 4)
        Text(
            text = "앱이름",
            fontSize = smallFont,
            color = Color.White
        )
    }
}

@Composable
fun TermsAgreeText() {
    Text(
        text = "이용 약관에\n\n동의 해주세요.",
        fontSize = 30.sp,
        color = Color.White,
        fontWeight = FontWeight.Bold
    )
}

@Composable
@Preview
fun TopScreenPreview() {
    TopScreenView()
}

@Composable
@Preview
fun AppNameInfoPreview() {
    AppNameInfo()
}