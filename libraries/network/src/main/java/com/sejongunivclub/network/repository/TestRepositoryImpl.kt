package com.sejongunivclub.network.repository

import com.sejongunivclub.network.network.TestApi
import com.sejongunivclub.network_api.repository.TestRepository
import com.sejongunivclub.network_api.response.TestResponse
import javax.inject.Inject

internal class TestRepositoryImpl @Inject constructor(private val testApi: TestApi) : TestRepository {
    override suspend fun getTestResponse(): TestResponse = testApi.getResponse()

}