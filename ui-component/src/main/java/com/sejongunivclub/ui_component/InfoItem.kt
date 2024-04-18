package com.sejongunivclub.ui_component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.sejongunivclub.ui_component.values.grayText
import com.sejongunivclub.ui_component.values.middleFont
import com.sejongunivclub.ui_component.values.veryTinyFont

@Composable
fun InfoItem(title: String, content: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = title, style = TextStyle(color = grayText), fontSize = veryTinyFont)
        VerticalSpacer(value = 2)
        Text(text = content, style = TextStyle(color = Color.White), fontSize = middleFont)
    }
}

@Composable
@Preview
fun InfoItemPreview() {
    InfoItem(title = "나이", content = "26")
}