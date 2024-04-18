package com.sejongunivclub.feature_squard.domain

import com.sejongunivclub.core.datasource.PositionPresetDataSource
import com.sejongunivclub.core.model.LocalScreen
import com.sejongunivclub.core.model.PositionPresetUIModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoadOtherUserPresetUseCase @Inject constructor(
    private val myScreen: LocalScreen,
    private val positionPresetDataSource: PositionPresetDataSource
) {

    suspend operator fun invoke(): PositionPresetUIModel {
        val preset = positionPresetDataSource.loadOtherUserPreset()
        val otherUserScreen = preset.screenSize
        return preset.copy(
            members = preset.members.map { member ->
                member.copy(
                    position = member.position.copy(
                        x = member.position.x * (myScreen.width / otherUserScreen.width).toFloat(),
                        y = member.position.y * (myScreen.height / otherUserScreen.height).toFloat()
                    )
                )
            }
        )
    }
}