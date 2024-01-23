package com.example.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.calendar.model.CalendarDate

@Composable
fun CalendarContent(
    calendar: List<List<CalendarDate?>>,
    onSelect: (CalendarDate) -> Unit
) {
    val selectedIndex = remember { mutableStateOf(-1 to -1) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .selectableGroup()
    ) {
        DaysOfWeek(Modifier.weight(0.5f))
        calendar.forEachIndexed { columnIndex, week ->
            Week(week, columnIndex, selectedIndex, onSelect)
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}