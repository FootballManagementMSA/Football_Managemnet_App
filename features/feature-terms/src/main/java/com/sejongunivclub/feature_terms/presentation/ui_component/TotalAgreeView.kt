package com.sejongunivclub.feature_terms.presentation.ui_component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sejongunivclub.feature_terms.R
import com.sejongunivclub.ui_component.VerticalSpacer
import com.sejongunivclub.ui_component.values.smallIcon
import com.sejongunivclub.ui_component.values.subTheme

@Composable
fun TotalAgreeView(onClick:() -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .height(50.dp)
            .background(subTheme),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            modifier = Modifier
                .wrapContentSize()
                .padding(start = 20.dp),
            onClick = onClick
        ) {
            Image(
                painter = painterResource(id = R.drawable.check_icon),
                contentDescription = "CheckIcon",
                modifier = Modifier.size(smallIcon)
            )
        }
        Text(text = "전체동의", color = Color(0xFF857FEB))
    }
    VerticalSpacer(value = 30)
}

@Preview
@Composable
fun TotalAgreeViewPreview() {
    TotalAgreeView(){}
}