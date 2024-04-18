package com.sejongunivclub.feature_squard.presentation

import com.sejongunivclub.core.model.UserData

sealed class SquadState {

    data object Loading : SquadState()

    data class Success(val data: UserData) : SquadState()
}