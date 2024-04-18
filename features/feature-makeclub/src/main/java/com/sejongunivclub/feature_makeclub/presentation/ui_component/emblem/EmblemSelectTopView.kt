package com.sejongunivclub.feature_makeclub.presentation.ui_component.emblem

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
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
fun EmblemSelectTopView(onClick: () -> Unit) {
    Column {
        BackArrowImageView(onClick)
        VerticalSpacer(value = 48)
        EmblemSelectText()
        VerticalSpacer(value = 30)
    }
}

@Composable
fun BackArrowImageView(onClick: () -> Unit) {
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
                    .align(Alignment.TopStart)
                    .clickable {
                        onClick()
                    },
                tint = Color.White
            )
        }
    }
}

@Composable
fun EmblemSelectText() {
    Text(
        text = "엠블럼을\n\n선택해주세요.",
        fontSize = 30.sp,
        color = Color.White,
        fontWeight = FontWeight.Bold
    )
}

@Composable
@Preview
fun EmblemSelectPreview() {
    EmblemSelectTopView({})
}