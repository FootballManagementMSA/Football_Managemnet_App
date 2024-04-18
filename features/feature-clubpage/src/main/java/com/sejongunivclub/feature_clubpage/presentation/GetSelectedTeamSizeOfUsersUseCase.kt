package com.sejongunivclub.feature_clubpage.presentation

import com.sejongunivclub.core.datasource.UserLocalDataSource
import javax.inject.Inject

class GetSelectedTeamSizeOfUsersUseCase @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource
) {
    suspend operator fun invoke(): Int {
        return userLocalDataSource.getSelectedTeamSizeOfUsers()
    }
}