package com.example.core.datasource

import com.example.core.ResultState.SquadResult
import com.example.network_api.entity.Users

interface SquadDataSource {
    suspend fun saveSquad(users: Users) : SquadResult
    suspend fun loadSquad() : SquadResult
}