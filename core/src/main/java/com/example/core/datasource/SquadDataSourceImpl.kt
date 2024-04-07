package com.example.core.datasource

import com.example.core.ResultState.SquadResult
import com.example.network_api.entity.Users
import com.example.network_api.repository.SquadRepository
import com.example.network_api.response.RespResult

class SquadDataSourceImpl(
    private val squadRepository: SquadRepository
) : SquadDataSource {
    override suspend fun saveSquad(users: Users): SquadResult {
        val result = squadRepository.saveSquad(users)
        return when (result) {
            is RespResult.Success -> {
                SquadResult.Success(result.data.status)

            }

            is RespResult.Error -> {
                SquadResult.Error(result.error.errorMessage)
            }
        }
    }

    override suspend fun loadSquad(): SquadResult {
        val result = squadRepository.loadSquad()
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