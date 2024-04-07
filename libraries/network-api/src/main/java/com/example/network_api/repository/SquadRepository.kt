package com.example.network_api.repository

import com.example.network_api.entity.Users
import com.example.network_api.response.DefaultApiResponse
import com.example.network_api.response.RespResult
import com.example.network_api.response.SquadResponse

interface SquadRepository {
    suspend fun saveSquad(users: Users): RespResult<DefaultApiResponse>
    suspend fun loadSquad(): RespResult<SquadResponse>
}