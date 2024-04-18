package com.sejongunivclub.network_api.repository

import com.sejongunivclub.network_api.response.JoinedClubInfoResponse
import com.sejongunivclub.network_api.response.MainHomeScheduleResponse
import com.sejongunivclub.network_api.response.MainHomeStudentDataResponse
import com.sejongunivclub.network_api.response.RespResult

interface MainHomeRepository {
    suspend fun loadData(): RespResult<MainHomeStudentDataResponse>
    suspend fun loadSchedule(): RespResult<MainHomeScheduleResponse>
    suspend fun getJoinedClubInfo(): RespResult<JoinedClubInfoResponse>
}