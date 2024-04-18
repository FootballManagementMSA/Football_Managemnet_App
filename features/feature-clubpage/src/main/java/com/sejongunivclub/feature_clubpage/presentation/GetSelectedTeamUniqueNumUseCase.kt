package com.sejongunivclub.feature_clubpage.presentation

import com.sejongunivclub.core.datasource.UserLocalDataSource
import javax.inject.Inject

class GetSelectedTeamUniqueNumUseCase @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource
) {
    suspend operator fun invoke(): String {
        return userLocalDataSource.getSelectedTeamUniqueNumber()
    }
}