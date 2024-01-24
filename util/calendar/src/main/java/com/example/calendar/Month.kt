package com.example.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.calendar.model.CalendarDate
import com.example.calendar.util.CalendarUtil

@Composable
fun Month(
    month: List<List<CalendarDate?>>,
    pageIndex: Int,
    onSelect: (CalendarDate) -> Unit
) {
    val selectedIndex = remember { mutableStateOf(-1 to -1) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .selectableGroup()
    ) {
        DaysOfWeek(Modifier.weight(0.5f))
        month.forEachIndexed { columnIndex, week ->
            Week(week, pageIndex , columnIndex, selectedIndex, onSelect)
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Preview
@Composable
fun MonthPreview() {
    Month(month = CalendarUtil.makeCalendar(2024, 1),0) {

    }
}