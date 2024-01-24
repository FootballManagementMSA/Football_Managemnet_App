package com.example.calendar.util

import android.os.Build
import com.example.calendar.model.CalendarDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.time.temporal.TemporalAdjusters
import java.util.Locale

object CalendarUtil {
    fun makeCalendar(year: Int, month: Int): List<List<CalendarDate?>> {
        val daysList = MutableList(5) { MutableList<CalendarDate?>(7) { null } }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val firstDayOfMonth = YearMonth.of(year, month).atDay(1)
            var currentDay =
                firstDayOfMonth.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.SUNDAY))

            for (week in 0 until 5) {
                for (dayOfWeek in 0 until 7) {
                    val adjustedYear =
                        if (month == 1 && currentDay.monthValue == 12) year - 1 else if (month == 12 && currentDay.monthValue == 1) year + 1 else year
                    if (currentDay.monthValue == month || currentDay.isBefore(firstDayOfMonth) || currentDay.monthValue == (month % 12) + 1) {
                        val dayName =
                            currentDay.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())
                        daysList[week][dayOfWeek] =
                            CalendarDate(
                                adjustedYear,
                                currentDay.monthValue,
                                currentDay.dayOfMonth,
                                dayName
                            )
                    }
                    currentDay = currentDay.plusDays(1)
                }
            }
        } else {
            val calendar = java.util.Calendar.getInstance().apply {
                set(java.util.Calendar.YEAR, year)
                set(java.util.Calendar.MONTH, month - 1)
                set(java.util.Calendar.DAY_OF_MONTH, 1)
            }

            val firstDayOfMonth = calendar.get(java.util.Calendar.DAY_OF_WEEK)
            calendar.add(java.util.Calendar.DATE, -firstDayOfMonth + java.util.Calendar.SUNDAY)

            for (week in 0 until 5) {
                for (dayOfWeek in 0 until 7) {
                    val adjustedYear = calendar.get(java.util.Calendar.YEAR)
                    val adjustedMonth =
                        calendar.get(java.util.Calendar.MONTH) + 1
                    val dayName =
                        calendar.getDisplayName(
                            java.util.Calendar.DAY_OF_WEEK,
                            java.util.Calendar.SHORT,
                            Locale.getDefault()
                        )

                    if (adjustedMonth == month || calendar.before(java.util.Calendar.getInstance().apply {
                            set(java.util.Calendar.YEAR, year)
                            set(java.util.Calendar.MONTH, month - 1)
                            set(java.util.Calendar.DAY_OF_MONTH, 1)
                        }) || adjustedMonth == (month % 12) + 1) {
                        daysList[week][dayOfWeek] = CalendarDate(
                            adjustedYear,
                            adjustedMonth,
                            calendar.get(java.util.Calendar.DAY_OF_MONTH),
                            dayName ?: ""
                        )
                    }

                    calendar.add(java.util.Calendar.DATE, 1)
                }
            }
        }

        return daysList
    }

}