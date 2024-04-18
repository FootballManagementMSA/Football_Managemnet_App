package com.sejongunivclub.feature_squard.presentation.ui_component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sejongunivclub.ui_component.R
import com.sejongunivclub.ui_component.VerticalSpacer
import com.sejongunivclub.ui_component.values.subTheme
import com.sejongunivclub.ui_component.values.tinyFont

@Composable
fun CandidateView() {
    Text(
        text = "교체 선수",
        fontSize = tinyFont,
        fontWeight = FontWeight.Bold,
        color = Color.White
    )
    VerticalSpacer(value = 10)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(subTheme)
            .wrapContentHeight()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Image(
            modifier = Modifier
                .padding(5.dp)
                .size(20.dp),
            painter = painterResource(id = R.drawable.cloth_icon),
            contentDescription = ""
        )
    }
}