package com.sejongunivclub.ui_component.buttons

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sejongunivclub.ui_component.values.mainTheme

@Composable
fun DarkButton(
    modifier: Modifier = Modifier,
    buttonText: String,
    textColor: Color,
    radius: Dp,
    onClick: () -> Unit
) {
    Column(modifier = modifier
        .wrapContentSize(align = Alignment.Center)) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .wrapContentSize()
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = mainTheme),
            contentPadding = PaddingValues(),
            shape = RoundedCornerShape(radius)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
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
fun DarkButtonPreview() {
    DarkButton(
        buttonText = "가입 신청",
        textColor = Color.White,
        radius = 20.dp
    ) {
    }
}