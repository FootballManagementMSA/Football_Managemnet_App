package com.example.feature_squard.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.ResultState.SquadResult
import com.example.core.model.MemberUiModel
import com.example.core.model.UserData
import com.example.feature_squard.domain.LoadMyPresetUseCase
import com.example.feature_squard.domain.LoadSquadUseCase
import com.example.feature_squard.domain.SavePositionPresetUseCase
import com.example.feature_squard.domain.SaveSquadUseCase
import com.example.feature_squard.presentation.SquadState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SquadViewModel @Inject constructor(
    private val saveSquadUseCase: SaveSquadUseCase,
    private val loadSquadUseCase: LoadSquadUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<SquadState>(SquadState.Loading)
    val uiState get() = _uiState

    fun loadPreset(
        teamId: Long,
        scheduleId: Long,
    ) = viewModelScope.launch {
        val result = loadSquadUseCase(teamId, scheduleId)
        when (result) {
            is SquadResult.Success<*> -> {
                _uiState.value = SquadState.Success(result.data as UserData)
            }

            is SquadResult.Error -> {
                Log.e("Error", result.errorMessage)
            }
        }
    }

    fun savePosition(
        teamId: Long,
        scheduleId: Long,
        users: UserData
    ) = viewModelScope.launch {
        saveSquadUseCase(teamId, scheduleId, users)
    }
}