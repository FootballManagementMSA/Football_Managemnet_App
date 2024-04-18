package com.sejongunivclub.feature_join.presentation.ui_component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sejongunivclub.ui_component.values.horizontalGradation
import com.sejongunivclub.ui_component.values.subTheme
import com.sejongunivclub.ui_component.values.transparentBrush
@Composable
fun SelectProfileButton(
    isSelected: Boolean,
    onClick: () -> Unit,
    contentDescription: String,
    position: String,
    modifier: Modifier = Modifier
) {
    val borderColor1 = if (isSelected) horizontalGradation else transparentBrush
    var TextColor=if(isSelected)Color.White else Color.Black
    Box(
        modifier = modifier
            .background(subTheme, shape = RoundedCornerShape(2.dp))
            .clickable { onClick() }
            .border(
                width = 1.dp,
                brush = borderColor1,
                shape = RoundedCornerShape(2.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = position,
            fontSize = 11.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
        )
    }
}

