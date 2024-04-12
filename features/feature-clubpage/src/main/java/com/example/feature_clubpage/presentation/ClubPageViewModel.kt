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
    private val getSelectedTeamCreatedAtUseCase: GetSelectedTeamCreatedAtUseCase,
    private val getSelectedTeamSizeOfUsersUseCase: GetSelectedTeamSizeOfUsersUseCase,
    private val getSelectedTeamUniqueNumUseCase: GetSelectedTeamUniqueNumUseCase,
    private val getSelectedTeamNameUseCase: GetSelectedTeamNameUseCase,
    private val getSelectedTeamEmblemUseCase: GetSelectedTeamEmblemUseCase
): ViewModel(){
    private val _createdAt = MutableStateFlow("")
    val createdAt: StateFlow<String> = _createdAt

    private val _sizeOfUsers = MutableStateFlow("")
    val sizeOfUsers: StateFlow<String> = _sizeOfUsers

    private val _uniqueNum = MutableStateFlow("")
    val uniqueNum: StateFlow<String> = _uniqueNum

    private val _teamName = MutableStateFlow("")
    val teamName: StateFlow<String> = _teamName

    private val _emblem = MutableStateFlow("")
    val emblem: StateFlow<String> = _emblem
    init {
        getCreatedAt()
        getTeamSizeOfUsers()
        getTeamUniqueNum()
        getTeamName()
        getTeamEmblem()
    }
    fun getCreatedAt() {
        viewModelScope.launch {
            _createdAt.value = getSelectedTeamCreatedAtUseCase()
        }
    }

    fun getTeamSizeOfUsers() {
        viewModelScope.launch {
            _sizeOfUsers.value = getSelectedTeamSizeOfUsersUseCase().toString()
        }
    }

    fun getTeamUniqueNum() {
        viewModelScope.launch {
            _uniqueNum.value = getSelectedTeamUniqueNumUseCase()
        }
    }

    fun getTeamName() {
        viewModelScope.launch {
            _teamName.value = getSelectedTeamNameUseCase()
        }
    }

    fun getTeamEmblem() {
        viewModelScope.launch {
            _emblem.value = getSelectedTeamEmblemUseCase()
        }
    }
}