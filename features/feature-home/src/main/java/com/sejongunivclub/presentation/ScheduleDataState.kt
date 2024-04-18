package com.sejongunivclub.presentation

import com.sejongunivclub.core.model.MainSchedule

sealed class ScheduleDataState {
    data class Success(val data: List<MainSchedule?>) : ScheduleDataState()
    data object Loading : ScheduleDataState()
}
