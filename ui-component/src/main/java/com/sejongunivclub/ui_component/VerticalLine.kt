package com.sejongunivclub.ui_component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sejongunivclub.ui_component.values.grayText

@Composable
fun VerticalLine() {
    Box(
        Modifier
            .fillMaxHeight()
            .padding(vertical = 16.dp)
            .width(1.dp)
            .background(grayText)
    )
}
