package com.sejongunivclub.feature_makeclub.presentation.ui_component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sejongunivclub.ui_component.buttons.CustomGradientButton
import com.sejongunivclub.ui_component.R
import com.sejongunivclub.ui_component.VerticalSpacer
import com.sejongunivclub.ui_component.values.gradientColorsList

@Composable
fun CompleteView(buttonText: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(50.dp)
        )
        {
            Image(
                painter = painterResource(id = R.drawable.check_icon),
                contentDescription = "CheckIcon",
                modifier = Modifier.size(100.dp)
            )
        }
        VerticalSpacer(value = 10)
        Text(
            text = "팀 생성이 \n\n 완료되었습니다.",
            fontSize = 30.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        VerticalSpacer(value = 200)
        CustomGradientButton(
            gradientColors = gradientColorsList,
            cornerRadius = 16.dp,
            buttonText = buttonText,
            roundedCornerShape = RoundedCornerShape(20.dp)
        ) {
            onClick()
        }

    }
}

@Preview
@Composable
fun CompleteViewPreview() {
    CompleteView(buttonText = "확인", {})
}