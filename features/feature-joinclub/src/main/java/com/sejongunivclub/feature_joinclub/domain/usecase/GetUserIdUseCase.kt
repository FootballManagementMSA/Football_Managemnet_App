package com.sejongunivclub.feature_joinclub.domain.usecase

import com.sejongunivclub.core.datasource.UserLocalDataSource
import javax.inject.Inject

class GetUserIdUseCase @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource
) {
    suspend operator fun invoke(): Long {
        return userLocalDataSource.getUserId()
    }
}