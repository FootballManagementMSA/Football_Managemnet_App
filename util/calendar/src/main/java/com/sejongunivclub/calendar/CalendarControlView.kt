package com.sejongunivclub.calendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.sejongunivclub.ui_component.HorizontalSpacer

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CalendarControlView(
    modifier: Modifier = Modifier,
    year: Int,
    month: Int,
    onPrev: () -> Unit,
    onNext: () -> Unit
) {
    Row(modifier) {
        Text(modifier= Modifier.testTag("date"),text = "$year 년 $month 월")
        HorizontalSpacer(value = 10)
        Icon(
            modifier = Modifier.testTag("prev").clickable { onPrev() },
            imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
            contentDescription = "key"
        )
        HorizontalSpacer(value = 10)
        Icon(
            modifier = Modifier.testTag("next").clickable { onNext() },
            imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
            contentDescription = "key"
        )
    }
}

@Preview
@Composable
fun CalendarControlViewPreview() {
    CalendarControlView(
        modifier = Modifier.fillMaxWidth(),
        year = 2024,
        month = 1,
        onNext = {}, onPrev = {}
    )
}