package com.sejongunivclub.ui_component.buttons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sejongunivclub.ui_component.values.smallFont
import com.sejongunivclub.ui_component.values.subTheme

@Composable
fun DefaultRoundedButton(
    modifier: Modifier = Modifier,
    buttonColor: Color = subTheme,
    textColor: Color = Color.White,
    cornerRadius: Dp,
    buttonText: String,
    onClick: () -> Unit
) {

    Button(
        modifier = modifier
            .fillMaxWidth(),
        onClick = onClick,
        shape = RoundedCornerShape(cornerRadius),
        colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
    ) {
        Text(
            modifier = Modifier.padding(4.dp),
            text = buttonText,
            fontSize = smallFont,
            color = textColor
        )
    }
}

@Preview
@Composable
fun DefaultRoundedButtonPreview() {
    DefaultRoundedButton(cornerRadius = 32.dp, buttonText = "hello") {

    }
}

