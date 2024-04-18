package com.sejongunivclub.feature_mypage.domain.usecase

import com.sejongunivclub.core.datasource.UserLocalDataSource
import javax.inject.Inject

class ClearDataStoreUseCase @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource
) {
    suspend operator fun invoke() = userLocalDataSource.clearDataStore()
}