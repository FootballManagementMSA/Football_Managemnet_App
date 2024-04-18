package com.sejongunivclub.feature_join.domain.usecase

import com.sejongunivclub.core.ResultState.JoinResult
import com.sejongunivclub.core.datasource.UserRemoteDataSource
import com.sejongunivclub.core.model.JoinModel
import javax.inject.Inject

class JoinUseCase @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource
) {
    suspend operator fun invoke(joinModel: JoinModel) : JoinResult {
        return userRemoteDataSource.join(joinModel)
    }
}
