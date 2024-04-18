package com.sejongunivclub.network_api.repository

import com.sejongunivclub.network_api.entity.Users
import com.sejongunivclub.network_api.response.DefaultApiResponse
import com.sejongunivclub.network_api.response.RespResult
import com.sejongunivclub.network_api.response.SquadResponse

interface SquadRepository {
    suspend fun saveSquad(
        teamId: Long,
        scheduleId: Long,
        users: Users
    ): RespResult<DefaultApiResponse>

    suspend fun loadSquad(
        teamId: Long,
        scheduleId: Long,
    ): RespResult<SquadResponse>
}