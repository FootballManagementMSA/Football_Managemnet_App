package com.sejongunivclub.ui_component.buttons

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sejongunivclub.ui_component.values.pink


@Composable
fun CharacteristicButton(
    onClick: () -> Unit,
    buttonColor: Color,
    textColor: Color,
    text: String

) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .size(width = 30.dp, height = 10.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = textColor,
                fontSize = 6.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
@Preview
fun SampleButtonPreView() {
    CharacteristicButton(onClick = {}, buttonColor = pink, textColor = Color.Black, text = "good")

}