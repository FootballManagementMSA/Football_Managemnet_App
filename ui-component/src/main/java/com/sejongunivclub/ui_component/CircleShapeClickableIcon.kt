package com.sejongunivclub.ui_component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sejongunivclub.ui_component.values.largeIcon
import com.sejongunivclub.ui_component.values.profileInfoButton

@Composable
fun CircleShapeClickableIcon(
    size: Dp,
    background: Color,
    @DrawableRes icon: Int,
    onAction: () -> Unit
) {
    Box(modifier = Modifier
        .size(size)
        .clip(CircleShape)
        .background(background)
        .clickable { onAction() }) {
        Icon(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(3.dp),
            painter = painterResource(id = icon),
            contentDescription = "Icon"
        )
    }
}

@Composable
@Preview
fun CircleShapeButtonPreview() {
    CircleShapeClickableIcon(largeIcon, profileInfoButton, R.drawable.league_icon) {
        //do nothing
    }
}
