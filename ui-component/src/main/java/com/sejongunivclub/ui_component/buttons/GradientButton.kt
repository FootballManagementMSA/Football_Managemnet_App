package com.sejongunivclub.ui_component.buttons

import androidx.compose.foundation.background
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sejongunivclub.ui_component.values.gradientColorsList

@Composable
fun CustomGradientButton(
    gradientColors: List<Color>,
    cornerRadius: Dp,
    buttonText: String,
    roundedCornerShape: RoundedCornerShape,
    onClick: () -> Unit
) {

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 32.dp, end = 32.dp),
        onClick = onClick,

        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(cornerRadius)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(colors = gradientColors),
                    shape = roundedCornerShape
                )
                .clip(roundedCornerShape)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = buttonText,
                fontSize = 13.sp,
                color = Color.White
            )
        }
    }
}

@Composable
fun DialogCustomGradientButton(
    modifier: Modifier = Modifier,
    gradientColors: List<Color>,
    buttonText: String,
    radius: Dp,
    onClick: () -> Unit
) {
    Column(modifier = modifier.wrapContentSize(align = Alignment.Center)) {
        Button(
            modifier = modifier
                .wrapContentSize()
                .fillMaxWidth(),
            onClick = onClick,

            contentPadding = PaddingValues(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.horizontalGradient(colors = gradientColors),
                        shape = RoundedCornerShape(radius)
                    )
                    .clip(RoundedCornerShape(radius))
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = buttonText,
                    fontSize = 15.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
fun FinalCustomGradientButtonPreview() {
    DialogCustomGradientButton(
        gradientColors = gradientColorsList,
        buttonText = "수락하기",
        radius = 20.dp
    ) {

    }
}
