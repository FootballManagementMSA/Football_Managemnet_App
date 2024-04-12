package com.example.feature_clubpage.presentation

import com.example.core.datasource.UserLocalDataSource
import javax.inject.Inject

class GetSelectedTeamSizeOfUsersUseCase @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource
) {
    suspend operator fun invoke(): Int {
        return userLocalDataSource.getSelectedTeamSizeOfUsers()
    }
}