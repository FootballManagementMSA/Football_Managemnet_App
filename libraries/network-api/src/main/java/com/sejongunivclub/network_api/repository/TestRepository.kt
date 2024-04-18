package com.sejongunivclub.network_api.repository

import com.sejongunivclub.network_api.response.TestResponse

interface TestRepository {
    suspend fun getTestResponse() : TestResponse
}