package com.example.feature_clubpage.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.model.ClubJoinRequestModel
import com.example.core.model.UserModel
import com.example.core.model.UserTeamInfoModel
import com.example.coroutine.IoDispatcher
import com.example.feature_clubpage.domain.usecase.GetJoinedMemberUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.invoke
import kotlinx.coroutines.launch
import javax.inject.Inject

class ClubPageViewModel @Inject constructor(
    private val getJoinedMemberUseCase: GetJoinedMemberUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher

) : ViewModel() {

    private val _joinedMember = MutableStateFlow<List<UserModel>>(listOf())
    val joinedMember: StateFlow<List<UserModel>> = _joinedMember

    init {
        clubMemberRequest()
    }

    fun clubMemberRequest() {
        viewModelScope.launch {
            (ioDispatcher){
                _joinedMember.value = getJoinedMemberUseCase()
            }
        }
    }


}