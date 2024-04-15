package com.example.core.datasource

import android.util.Log
import com.example.core.ResultState.MakeClubResult
import com.example.core.ResultState.MakeClubScheduleResult
import com.example.core.mapper.UiModelMapper.mapToUiModel
import com.example.core.model.ClubInfo
import com.example.core.model.ClubSchedule
import com.example.core.model.ClubSchedule.Companion.mapToEntity
import com.example.core.model.LocationInfo
import com.example.core.model.MakeClubModel
import com.example.core.model.Map
import com.example.core.model.UserModel
import com.example.core.model.UserTeamInfoModel
import com.example.core.util.FormDataUtil
import com.example.network_api.repository.ClubRepository
import com.example.network_api.response.RespResult
import javax.inject.Inject

class ClubDataSourceImpl @Inject constructor(
    private val clubRepository: ClubRepository,
    private val userLocalDataSource: UserLocalDataSource
) : ClubDataSource {
    override suspend fun sendClubInfo(makeClubModel: MakeClubModel): MakeClubResult {
        val requestName = FormDataUtil.mapToRequestBody(makeClubModel.name)
        val requestDetails = FormDataUtil.mapToRequestBody(makeClubModel.details)
        val result = clubRepository.sendClubInfo(
            requestName,
            requestDetails,
            FormDataUtil.mapToMultipart("emblem", makeClubModel.emblem)
        )
        return when (result) {
            is RespResult.Success -> {
                MakeClubResult.Success(result.data.unique.uniqueNumber)
            }

            is RespResult.Error -> {
                MakeClubResult.Error(result.error.errorMessage)
            }
        }
    }

    override suspend fun searchClub(code: String): List<ClubInfo> {
        return clubRepository.searchClub(code).mapToUiModel().data
    }


    override suspend fun searchMap(code: String): List<LocationInfo> {
        return clubRepository.searchMap(code).mapToUiModel().items
    }




    override suspend fun createClubSchedule(
        teamId: Long,
        clubSchedule: ClubSchedule
    ): MakeClubScheduleResult {
        return when (val result =
            clubRepository.createClubSchedule(teamId, clubSchedule.mapToEntity())) {
            is RespResult.Success -> {
                MakeClubScheduleResult.Success(result.data.status)
            }

            is RespResult.Error -> {
                MakeClubScheduleResult.Error(result.error.errorMessage)
            }
        }
    }

    override suspend fun getJoinedClub(): List<UserTeamInfoModel> {
        val userId = userLocalDataSource.getUserId()
        return clubRepository.getJoinedClub(userId).mapToUiModel().data
    }

    override suspend fun getJoinedMember(): List<UserModel> {
        val teamId=userLocalDataSource.getSelectedTeamId()
        Log.d("teamid입니다",teamId.toString())

        return clubRepository.getJoinedMember(teamId).mapToUiModel().data
    }

}