package com.sejongunivclub.ui_component.template

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.sejongunivclub.ui_component.HorizontalSpacer
import com.sejongunivclub.ui_component.R
import com.sejongunivclub.ui_component.VerticalSpacer
import com.sejongunivclub.ui_component.values.bigFont
import com.sejongunivclub.ui_component.values.smallFont

@Composable
fun DefaultDialog(
    header: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    val config = LocalConfiguration.current
    Dialog(
        onDismissRequest = {},
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = true),
    ) {
        Surface(
            modifier = Modifier
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(20.dp)
                    .heightIn(min = 270.dp)
                    .height(config.screenHeightDp.dp / 3)
            ) {
                header()
                Spacer(modifier = Modifier.weight(1f))
                content()
            }
        }
    }
}

@Composable
fun Header(onDismiss: () -> Unit, title: String, userName: String) {
    DialogTopView(
        modifier = Modifier.fillMaxWidth(),
        title = title,
        onDismiss = onDismiss
    )
    VerticalSpacer(value = 10)
    DialogProfileView(userName = userName)
}

@Composable
private fun DialogTopView(modifier: Modifier = Modifier, title: String, onDismiss: () -> Unit) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title, fontSize = smallFont, fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = null,
            modifier = Modifier.clickable { onDismiss() })
    }
    VerticalSpacer(value = 10)
}

@Composable
private fun DialogProfileView(modifier: Modifier = Modifier, userName: String) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.default_profile_image),
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
        )
        HorizontalSpacer(value = 10)
        Text(text = userName, fontSize = bigFont, fontWeight = FontWeight.Bold)
        HorizontalSpacer(value = 4)
        Text(text = "님")
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null
        )
    }
    VerticalSpacer(value = 10)
}

@Preview
@Composable
fun DefaultDialogPreview() {
    DefaultDialog(
        header = { Header(onDismiss = {}, title = "구단 가입 신청입니다.", userName = "임성우") },
        content = {  }
    )
}