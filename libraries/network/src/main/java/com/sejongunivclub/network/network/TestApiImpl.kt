package com.sejongunivclub.network.network

import com.sejongunivclub.network_api.response.TestResponse

// 순수 더미 클래스
internal class TestApiImpl : TestApi {
    override suspend fun getResponse(): TestResponse = TestResponse("Http 200")
}