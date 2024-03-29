package com.example.network_api.repository

import com.example.network_api.response.JoinedClubInfoResponse
import com.example.network_api.response.MainHomeScheduleResponse
import com.example.network_api.response.MainHomeStudentDataResponse
import com.example.network_api.response.RespResult

interface MainHomeRepository {
    suspend fun loadData(): RespResult<MainHomeStudentDataResponse>
    suspend fun loadSchedule(): RespResult<MainHomeScheduleResponse>
    suspend fun getJoinedClubInfo(): RespResult<JoinedClubInfoResponse>
}