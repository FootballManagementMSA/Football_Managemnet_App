package com.sejongunivclub.feature_join.presentation.ui_component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.R

@Composable
fun IconView() {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_call_answer),
                contentDescription = null,
                modifier = Modifier
                    .size(72.dp)
                    .align(Alignment.Center),
                tint = Color.White
            )
        }
    }

}
@Composable
@Preview
fun IconViewPreview() {
    IconView()
}