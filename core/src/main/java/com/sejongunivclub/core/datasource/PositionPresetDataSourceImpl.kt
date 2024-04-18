package com.sejongunivclub.core.datasource

import com.sejongunivclub.core.mapper.EntityMapper.mapToEntity
import com.sejongunivclub.core.mapper.UiModelMapper.mapToUiModel
import com.sejongunivclub.core.model.PositionPresetUIModel
import com.sejongunivclub.network_api.repository.TempSquadRepository
import javax.inject.Inject
import javax.inject.Singleton

//임시 테스트용
@Singleton
internal class PositionPresetDataSourceImpl @Inject constructor(private val tempSquadRepository: TempSquadRepository) :
    PositionPresetDataSource {

    override suspend fun save(positionPresetUIModel: PositionPresetUIModel) {
        tempSquadRepository.saveMyCustomSquadPreset(positionPresetUIModel.mapToEntity())
    }

    override suspend fun loadMyPreset(): PositionPresetUIModel {
        return tempSquadRepository.loadMyCustomSquadPreset().mapToUiModel()
    }

    override suspend fun loadOtherUserPreset(): PositionPresetUIModel {
        return tempSquadRepository.loadOtherUserCustomSquadPreset().mapToUiModel()
    }
}