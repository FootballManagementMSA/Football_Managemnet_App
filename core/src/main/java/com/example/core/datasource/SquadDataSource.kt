package com.example.core.datasource

import com.example.core.ResultState.SquadResult
import com.example.core.model.UserData
import com.example.network_api.entity.Users

interface SquadDataSource {
    suspend fun saveSquad(
        teamId: Long,
        scheduleId: Long,
        users: UserData
    ): SquadResult

    suspend fun loadSquad(
        teamId: Long,
        scheduleId: Long,
    ): SquadResult
}