package com.sejongunivclub.feature_makeclub.presentation.ui_component.make_club

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sejongunivclub.ui_component.VerticalSpacer

@Composable
fun ClubDetailsView() {
    Column {
        Text(text = "구단 세부 내용", modifier = Modifier.padding(start = 10.dp), color = Color.White)
    }
    VerticalSpacer(value = 15)
}

@Preview
@Composable
fun ClubDetailsViewPreview() {
    ClubNameView()
}