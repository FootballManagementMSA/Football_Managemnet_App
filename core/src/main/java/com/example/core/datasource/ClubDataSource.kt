package com.example.core.datasource

import com.example.core.ResultState.MakeClubResult
import com.example.core.model.ClubInfo
import com.example.core.model.MakeClubModel
import com.example.core.model.UserTeamInfoModel

interface ClubDataSource {
    suspend fun sendClubInfo(makeClubModel: MakeClubModel): MakeClubResult
    suspend fun searchClub(code: String): List<ClubInfo>
    suspend fun getJoinedClub(): List<UserTeamInfoModel>
}