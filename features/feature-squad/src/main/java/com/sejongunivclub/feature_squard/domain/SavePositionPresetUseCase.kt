package com.sejongunivclub.feature_squard.domain

import com.sejongunivclub.core.datasource.PositionPresetDataSource
import com.sejongunivclub.core.model.LocalScreen
import com.sejongunivclub.core.model.PositionPresetUIModel
import com.sejongunivclub.core.model.MemberUiModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SavePositionPresetUseCase @Inject constructor(
    private val myScreen: LocalScreen,
    private val positionPresetDataSource: PositionPresetDataSource
) {

    suspend operator fun invoke(positions: List<MemberUiModel>) {
        positionPresetDataSource.save(
            positionPresetUIModel = PositionPresetUIModel(
                screenSize = myScreen,
                members = positions
            )
        )
    }
}