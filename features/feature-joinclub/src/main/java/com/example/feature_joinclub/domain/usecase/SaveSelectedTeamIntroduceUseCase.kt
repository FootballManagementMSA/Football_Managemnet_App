package com.example.feature_joinclub.domain.usecase

import com.example.core.datasource.UserLocalDataSource
import javax.inject.Inject

class SaveSelectedTeamIntroduceUseCase @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource
) {
    suspend operator fun invoke(introduce: String) {
        userLocalDataSource.saveSelectedTeamIntroduce(introduce)
    }
}