package com.sejongunivclub.calendar.values

import androidx.compose.ui.unit.dp

val dayOfWeekToCalendarDay = mapOf(
    "Sun" to java.util.Calendar.SUNDAY,
    "Mon" to java.util.Calendar.MONDAY,
    "Tue" to java.util.Calendar.TUESDAY,
    "Wed" to java.util.Calendar.WEDNESDAY,
    "Thu" to java.util.Calendar.THURSDAY,
    "Fri" to java.util.Calendar.FRIDAY,
    "Sat" to java.util.Calendar.SATURDAY,
    "일" to java.util.Calendar.SUNDAY,
    "월" to java.util.Calendar.MONDAY,
    "화" to java.util.Calendar.TUESDAY,
    "수" to java.util.Calendar.WEDNESDAY,
    "목" to java.util.Calendar.THURSDAY,
    "금" to java.util.Calendar.FRIDAY,
    "토" to java.util.Calendar.SATURDAY
)

val dayOfWeekList = listOf("일", "월", "화", "수", "목", "금", "토")

val calendarDateSize = 28.dp