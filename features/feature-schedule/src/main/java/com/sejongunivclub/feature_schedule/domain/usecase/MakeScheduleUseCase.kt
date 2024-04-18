package com.sejongunivclub.feature_schedule.domain.usecase

import com.sejongunivclub.core.datasource.ClubDataSource
import com.sejongunivclub.core.model.ClubSchedule
import javax.inject.Inject

class MakeScheduleUseCase @Inject constructor(
    private val clubDataSource: ClubDataSource
) {
    suspend operator fun invoke(teamId: Long, clubSchedule: ClubSchedule) =
        clubDataSource.createClubSchedule(teamId, clubSchedule)
}