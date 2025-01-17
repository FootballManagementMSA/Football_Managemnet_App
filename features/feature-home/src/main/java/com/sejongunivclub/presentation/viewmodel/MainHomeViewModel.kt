package com.sejongunivclub.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sejongunivclub.core.model.JoinedClubData
import com.sejongunivclub.domain.usecase.GetJoinedClubInfoUseCase
import com.sejongunivclub.domain.usecase.LoadScheduleUseCase
import com.sejongunivclub.domain.usecase.LoadStudentDataUseCase
import com.sejongunivclub.presentation.ScheduleDataState
import com.sejongunivclub.presentation.StudentDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainHomeViewModel @Inject constructor(
    private val loadStudentDataUseCase: LoadStudentDataUseCase,
    private  val loadScheduleUseCase: LoadScheduleUseCase,
    private val getJoinedClubInfoUseCase: GetJoinedClubInfoUseCase
): ViewModel() {
    private val tempDispatcher = Dispatchers.IO
    private val _uiState = MutableStateFlow<StudentDataState>(StudentDataState.Loading)
    val uiState get() = _uiState.asStateFlow()

    private val _scheduleUiState = MutableStateFlow<ScheduleDataState>(ScheduleDataState.Loading)
    val scheduleUiState get() = _scheduleUiState.asStateFlow()

    private val _joinedClubInfo = MutableStateFlow<List<JoinedClubData>>(listOf())
    val joinedClubInfo: StateFlow<List<JoinedClubData>> = _joinedClubInfo

    fun getResponse() = viewModelScope.launch(tempDispatcher) {
        _uiState.value = StudentDataState.Success(loadStudentDataUseCase())
    }

    fun getScheduleData() = viewModelScope.launch(tempDispatcher) {
        _scheduleUiState.value = ScheduleDataState.Success(loadScheduleUseCase())
    }

    fun getJoinedClubInfo() = viewModelScope.launch(tempDispatcher) {
        _joinedClubInfo.value = getJoinedClubInfoUseCase()
    }
}