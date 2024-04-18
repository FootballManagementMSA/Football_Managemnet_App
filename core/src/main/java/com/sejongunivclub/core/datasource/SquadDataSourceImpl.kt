package com.sejongunivclub.core.datasource

import com.sejongunivclub.core.ResultState.SquadResult
import com.sejongunivclub.core.model.User.Companion.toEntity
import com.sejongunivclub.core.model.UserData
import com.sejongunivclub.network_api.entity.Users
import com.sejongunivclub.network_api.repository.SquadRepository
import com.sejongunivclub.network_api.response.RespResult
import javax.inject.Inject

class SquadDataSourceImpl @Inject constructor(
    private val squadRepository: SquadRepository
) : SquadDataSource {
    override suspend fun saveSquad(
        teamId: Long,
        scheduleId: Long,
        users: UserData
    ): SquadResult {
        val result = squadRepository.saveSquad(
            teamId,
            scheduleId,
            Users(users.users.mapIndexed { index, user -> user.toEntity(index) })
        )
        return when (result) {
            is RespResult.Success -> {
                SquadResult.Success(result.data.status)

            }

            is RespResult.Error -> {
                SquadResult.Error(result.error.errorMessage)
            }
        }
    }

    override suspend fun loadSquad(
        teamId: Long,
        scheduleId: Long,
    ): SquadResult {
        val result = squadRepository.loadSquad(teamId, scheduleId)
        return when (result) {
            is RespResult.Success -> {
                SquadResult.Success(result.data.data)
            }

            is RespResult.Error -> {
                SquadResult.Error(result.error.errorMessage)
            }
        }
    }
}