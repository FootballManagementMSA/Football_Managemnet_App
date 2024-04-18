package com.sejongunivclub.core.datasource

import com.sejongunivclub.core.ResultState.SquadResult
import com.sejongunivclub.core.model.UserData

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