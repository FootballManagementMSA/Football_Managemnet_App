package com.sejongunivclub.calendar.model

data class CalendarPage(
    val year: Int,
    val month: Int,
    val calendar: List<List<Date?>>
)
