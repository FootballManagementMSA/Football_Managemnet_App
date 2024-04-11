package com.example.feature_squard.presentation

import com.example.core.model.PositionPresetUIModel
import com.example.core.model.UserData

sealed class SquadState {

    data object Loading : SquadState()

    data class Success(val data: UserData) : SquadState()
}