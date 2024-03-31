package com.example.feature_clubpage.presentation

import com.example.core.datasource.UserLocalDataSource
import javax.inject.Inject

class GetSelectedTeamCreatedAtUseCase @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource
) {
    suspend operator fun invoke(): String {
        return userLocalDataSource.getSelectedTeamCreatedAt()
    }
}