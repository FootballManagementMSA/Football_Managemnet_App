package com.sejongunivclub.feature_makeclub.presentation.ui_component.make_club

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sejongunivclub.ui_component.HorizontalSpacer
import com.sejongunivclub.ui_component.R
import com.sejongunivclub.ui_component.VerticalSpacer
import com.sejongunivclub.ui_component.values.lastGradientColor
import com.sejongunivclub.ui_component.values.tinyFont

@Composable
fun ClubIdInfoBox(text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .fillMaxWidth(),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(20.dp)
                .clickable {}
        )
        {
            Image(
                painter = painterResource(id = R.drawable.check_icon),
                contentDescription = "CheckIcon",
                modifier = Modifier.size(12.dp)
            )
        }
        HorizontalSpacer(value = 5)
        Text(
            text = text,
            color = lastGradientColor,
            maxLines = 1,
            style = TextStyle(
                fontSize = tinyFont
            )
        )

    }
    VerticalSpacer(value = 20)
}

@Composable
@Preview
fun ClubIdInfoBoxPreview() {
    ClubIdInfoBox("preview")
}