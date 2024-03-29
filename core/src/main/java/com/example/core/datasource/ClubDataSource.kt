package com.example.core.datasource

import com.example.core.ResultState.MakeClubResult
import com.example.core.ResultState.MakeClubScheduleResult
import com.example.core.model.ClubInfo
import com.example.core.model.MakeClubModel
import com.example.core.model.UserTeamInfoModel
import com.example.core.model.ClubSchedule

interface ClubDataSource {
    suspend fun sendClubInfo(makeClubModel: MakeClubModel): MakeClubResult
    suspend fun searchClub(code: String): List<ClubInfo>
    suspend fun createClubSchedule(teamId: Long, clubSchedule: ClubSchedule) : MakeClubScheduleResult
    suspend fun getJoinedClub(): List<UserTeamInfoModel>
}