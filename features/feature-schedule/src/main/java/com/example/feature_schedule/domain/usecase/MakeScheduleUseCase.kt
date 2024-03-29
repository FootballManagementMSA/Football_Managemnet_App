package com.example.feature_schedule.domain.usecase

import com.example.core.datasource.ClubDataSource
import com.example.core.model.ClubSchedule
import javax.inject.Inject

class MakeScheduleUseCase @Inject constructor(
    private val clubDataSource: ClubDataSource
) {
    suspend operator fun invoke(teamId: Long, clubSchedule: ClubSchedule) =
        clubDataSource.createClubSchedule(teamId, clubSchedule)
}