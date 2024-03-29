package com.example.feature_join.domain.usecase

import com.example.core.ResultState.JoinResult
import com.example.core.datasource.UserRemoteDataSource
import com.example.core.model.JoinModel
import javax.inject.Inject

class JoinUseCase @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource
) {
    suspend operator fun invoke(joinModel: JoinModel) : JoinResult {
        return userRemoteDataSource.join(joinModel)
    }
}
