package com.sejongunivclub.core.datasource

import com.sejongunivclub.core.ResultState.MakeClubResult
import com.sejongunivclub.core.ResultState.MakeClubScheduleResult
import com.sejongunivclub.core.model.ClubInfo
import com.sejongunivclub.core.model.MakeClubModel
import com.sejongunivclub.core.model.UserTeamInfoModel
import com.sejongunivclub.core.model.ClubSchedule
import com.sejongunivclub.core.model.LocationInfo

interface ClubDataSource {
    suspend fun sendClubInfo(makeClubModel: MakeClubModel): MakeClubResult
    suspend fun searchClub(code: String): List<ClubInfo>
    suspend fun createClubSchedule(teamId: Long, clubSchedule: ClubSchedule) : MakeClubScheduleResult
    suspend fun getJoinedClub(): List<UserTeamInfoModel>
    suspend fun searchMap(code:String):List<LocationInfo>
}