package com.sejongunivclub.core.datasource

import com.sejongunivclub.core.model.JoinedClubData
import com.sejongunivclub.core.model.MainHomeScheduleUiModel
import com.sejongunivclub.core.model.MainHomeStudentDataUiModel

interface MainHomeDataSource {
    suspend fun loadStudentData() : MainHomeStudentDataUiModel
    suspend fun loadSchedule() : MainHomeScheduleUiModel
    suspend fun getJoinedClubInfo() : List<JoinedClubData>
}