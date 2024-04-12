package com.example.feature_squard.domain

import com.example.core.datasource.SquadDataSource
import com.example.core.model.UserData
import com.example.core.model.Users
import com.example.core.model.Users.Companion.toEntity
import javax.inject.Inject

class SaveSquadUseCase @Inject constructor(
    private val squadDataSource: SquadDataSource
) {
    suspend operator fun invoke(
        teamId: Long,
        scheduleId: Long,
        userData: UserData
    ) {
        squadDataSource.saveSquad(teamId, scheduleId, userData)
    }
}