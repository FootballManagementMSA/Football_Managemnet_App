package com.example.feature_joinclub.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.ResultState.ClubJoinRequestResult
import com.example.core.model.ClubInfo
import com.example.core.model.ClubJoinRequestModel
import com.example.core.model.UserTeamInfoModel
import com.example.coroutine.IoDispatcher
import com.example.feature_joinclub.domain.usecase.ClubJoinRequestUseCase
import com.example.feature_joinclub.domain.usecase.GetJoinedClubUseCase
import com.example.feature_joinclub.domain.usecase.SaveSelectedTeamUniqueNumUseCase
import com.example.feature_joinclub.domain.usecase.SearchClubUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClubSearchViewModel @Inject constructor(
    private val searchClubUseCase: SearchClubUseCase,
    private val clubJoinRequestUseCase: ClubJoinRequestUseCase,
    private val getJoinedClubUseCase: GetJoinedClubUseCase,
    private val saveSelectedTeamUniqueNumUseCase: SaveSelectedTeamUniqueNumUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel(){
    private val _searchValue = MutableStateFlow("")
    val searchValue: StateFlow<String> =_searchValue

    private val _searchedClub = MutableStateFlow<List<ClubInfo>>(listOf())
    val searchedClub: StateFlow<List<ClubInfo>> = _searchedClub

    private val _introduceValue = MutableStateFlow("")
    val introduceValue: StateFlow<String> =_introduceValue

    private val _userIdValue = MutableStateFlow("")
    val userIdValue: StateFlow<String> =_userIdValue

    private val _JoinResult = MutableSharedFlow<ClubJoinRequestResult>(replay = 1)
    val JoinResult: SharedFlow<ClubJoinRequestResult> = _JoinResult.asSharedFlow()

    private val _joinedClub = MutableStateFlow<List<UserTeamInfoModel>>(listOf())
    val joinedClub : StateFlow<List<UserTeamInfoModel>> = _joinedClub

    init {
        getJoinedClubList()
    }

    fun searchClub(clubName: String) {
        _searchValue.value = clubName
        viewModelScope.launch(ioDispatcher) {
            _searchedClub.value = searchClubUseCase(clubName)
        }
    }
    fun clubJoinReqeust(userId:Int,introduce:String){
        _introduceValue.value=introduce
        _userIdValue.value=userId.toString()
        viewModelScope.launch(ioDispatcher) {
            val result = clubJoinRequestUseCase(
                ClubJoinRequestModel(
                    1,"2"
                )
            )
            _JoinResult.emit(result)
        }


    }

    fun getJoinedClubList() {
        viewModelScope.launch(ioDispatcher) {
            _joinedClub.value = getJoinedClubUseCase()
        }
    }

    fun saveSelectedTeamUniqueNum(uniqueNum: String) {
        viewModelScope.launch {
            saveSelectedTeamUniqueNumUseCase(uniqueNum)
        }
    }
}
