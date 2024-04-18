package com.sejongunivclub.feature_makeclub.presentation.ui_component.emblem

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.sejongunivclub.ui_component.values.lastGradientColor
import com.sejongunivclub.ui_component.values.tinyFont

@Composable
fun EmblemInfoBox(text: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = text,
            color = lastGradientColor,
            maxLines = 1,
            style = TextStyle(
                fontSize = tinyFont
            )
        )
    }
}

@Preview
@Composable
fun EmblemInfoBoxPreview() {
    EmblemInfoBox(text = "preview")
}