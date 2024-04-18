package com.sejongunivclub.ui_component.buttons

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WhiteButton(
    buttonText: String,
    textColor: Color,
    roundedCornerShape: RoundedCornerShape,
    onClick: () -> Unit
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(align = Alignment.Center)) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .wrapContentSize()
                .fillMaxWidth(0.5f),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            contentPadding = PaddingValues(15.dp),
            shape = RoundedCornerShape(20.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(roundedCornerShape)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = buttonText,
                    fontSize = 15.sp,
                    color = textColor
                )
            }
        }
    }

}

@Composable
@Preview
fun WhiteButtonPreview() {
    WhiteButton(
        buttonText = "preview",
        textColor = Color.Magenta,
        roundedCornerShape = RoundedCornerShape(20.dp)
    ) {
    }
}