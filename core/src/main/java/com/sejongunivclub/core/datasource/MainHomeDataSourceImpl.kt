package com.sejongunivclub.core.datasource

import com.sejongunivclub.core.mapper.UiModelMapper.mapToUiModel
import com.sejongunivclub.core.model.JoinedClubData
import com.sejongunivclub.core.model.MainHomeScheduleUiModel
import com.sejongunivclub.core.model.MainHomeStudentDataUiModel
import com.sejongunivclub.core.util.JoinedClubDataUtil
import com.sejongunivclub.network_api.repository.MainHomeRepository
import com.sejongunivclub.network_api.response.RespResult
import javax.inject.Inject

class MainHomeDataSourceImpl @Inject constructor(
    private val mainHomeRepository: MainHomeRepository
) : MainHomeDataSource {
    override suspend fun loadStudentData(): MainHomeStudentDataUiModel {
        return mainHomeRepository.loadData().mapToUiModel()
    }

    override suspend fun loadSchedule(): MainHomeScheduleUiModel {
        return mainHomeRepository.loadSchedule().mapToUiModel()
    }

    override suspend fun getJoinedClubInfo(): List<JoinedClubData> {
        return when (val result = mainHomeRepository.getJoinedClubInfo()) {
            is RespResult.Success -> {
                JoinedClubDataUtil.setClubDataList(result.mapToUiModel().data)
                result.mapToUiModel().data
            }

            is RespResult.Error -> {
                result.mapToUiModel().data
            }
        }
    }
}