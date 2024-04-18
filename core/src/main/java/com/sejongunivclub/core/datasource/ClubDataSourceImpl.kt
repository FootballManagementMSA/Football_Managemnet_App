package com.sejongunivclub.core.datasource

import com.sejongunivclub.core.ResultState.MakeClubResult
import com.sejongunivclub.core.ResultState.MakeClubScheduleResult
import com.sejongunivclub.core.mapper.UiModelMapper.mapToUiModel
import com.sejongunivclub.core.model.ClubInfo
import com.sejongunivclub.core.model.ClubSchedule
import com.sejongunivclub.core.model.ClubSchedule.Companion.mapToEntity
import com.sejongunivclub.core.model.LocationInfo
import com.sejongunivclub.core.model.MakeClubModel
import com.sejongunivclub.core.model.UserTeamInfoModel
import com.sejongunivclub.core.util.FormDataUtil
import com.sejongunivclub.network_api.repository.ClubRepository
import com.sejongunivclub.network_api.response.RespResult
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




}