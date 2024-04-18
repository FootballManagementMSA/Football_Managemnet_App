package com.sejongunivclub.calendar

import com.sejongunivclub.calendar.model.CalendarPage

sealed class CalendarState {
    data object Loading : CalendarState()
    data class Success(val calendarPage: List<CalendarPage>) : CalendarState()
}