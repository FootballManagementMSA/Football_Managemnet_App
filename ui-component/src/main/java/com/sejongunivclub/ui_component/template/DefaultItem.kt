package com.sejongunivclub.ui_component.template

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sejongunivclub.ui_component.values.subTheme

@Composable
fun DefaultItem(
    modifier: Modifier = Modifier,
    radius: Dp,
    color: Color = subTheme,
    content: @Composable () -> Unit
) {
    val config = LocalConfiguration.current
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(radius))
            .background(color)
            .fillMaxWidth()
            .heightIn(min = 150.dp, max = 200.dp)
            .height(config.screenHeightDp.dp / 10)
    ) {
        content()
    }
}

@Composable
@Preview
fun DefaultItemPreview() {
    DefaultItem(radius = 16.dp) {
        Text(text = "hi")
    }
}