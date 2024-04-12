package com.example.feature_clubpage.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClubPageViewModel @Inject constructor(
    private val getSelectedTeamCreatedAtUseCase: GetSelectedTeamCreatedAtUseCase
): ViewModel(){
    private val _createdAt = MutableStateFlow("")
    val createdAt: StateFlow<String> = _createdAt
    init {
        getCreatedAt()
    }
    fun getCreatedAt() {
        viewModelScope.launch {
            _createdAt.value = getSelectedTeamCreatedAtUseCase()
        }
    }
}