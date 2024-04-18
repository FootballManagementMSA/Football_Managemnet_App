package com.sejongunivclub.feature_mypage.presentation.ui_component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sejongunivclub.ui_component.values.appBarFont
import com.sejongunivclub.ui_component.values.largeIcon

@Composable
fun MyPageTopView(
    modifier: Modifier = Modifier,
    title: String,
    onBack: () -> Unit,
    onDesroy: () -> Unit
) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(start = 40.dp, top = 40.dp, end = 40.dp)
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBack) {
            Icon(
                modifier = Modifier.size(largeIcon),
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "NavIcon",
                tint = Color.White
            )
        }
        Text(text = title, style = TextStyle(color = Color.White), fontSize = appBarFont)
        IconButton(onClick = onDesroy) {
            Icon(
                modifier = Modifier.size(largeIcon),
                imageVector = Icons.Default.Close,
                contentDescription = "Action",
                tint = Color.White
            )
        }
    }
}

@Preview
@Composable
fun MyPageTopViewPreview() {
    MyPageTopView(modifier = Modifier, "마이페이지", {}, {})
}