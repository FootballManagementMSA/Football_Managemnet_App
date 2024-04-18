package com.sejongunivclub.feature_squard.domain

import com.sejongunivclub.core.datasource.PositionPresetDataSource
import com.sejongunivclub.core.model.LocalScreen
import com.sejongunivclub.core.model.PositionPresetUIModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoadMyPresetUseCase @Inject constructor(
    private val myScreen: LocalScreen,
    private val positionPresetDataSource: PositionPresetDataSource
) {

    //자신의 폰에도 화면 비율을 구하는 이유는
    //만약 기종이 다른 폰으로 로그인 할 경우 화면의 크기가 달라지기 때문이다.
    suspend operator fun invoke(): PositionPresetUIModel {
        val preset = positionPresetDataSource.loadMyPreset()
        val screenFromDB = preset.screenSize
        return preset.copy(
            members = preset.members.map { member ->
                member.copy(
                    position = member.position.copy(
                        x = member.position.x * (myScreen.width / screenFromDB.width).toFloat(),
                        y = member.position.y * (myScreen.height / screenFromDB.height).toFloat()
                    )
                )
            }
        )
    }
}