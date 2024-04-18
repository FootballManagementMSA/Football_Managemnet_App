package com.sejongunivclub.feature_dialog.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sejongunivclub.coroutine.IoDispatcher
import com.sejongunivclub.feature_dialog.domain.GetJoinClubUserInfoUseCase
import com.sejongunivclub.feature_dialog.presentation.DialogState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DialogViewModel @Inject constructor(
    private val getJoinClubUserInfoUseCase: GetJoinClubUserInfoUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _dialogUiState = MutableStateFlow<DialogState>(DialogState.Loading)
    val dialogUiState: StateFlow<DialogState> = _dialogUiState

    init {
        getJoinClubUserInfo()
    }

    fun getJoinClubUserInfo() {
        viewModelScope.launch(ioDispatcher) {
            _dialogUiState.value = DialogState.Success(getJoinClubUserInfoUseCase())
        }
    }

}