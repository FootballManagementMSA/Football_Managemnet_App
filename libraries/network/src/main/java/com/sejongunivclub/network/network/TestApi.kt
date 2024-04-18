package com.sejongunivclub.network.network

import com.sejongunivclub.network_api.response.TestResponse

interface TestApi {
    suspend fun getResponse() : TestResponse
}