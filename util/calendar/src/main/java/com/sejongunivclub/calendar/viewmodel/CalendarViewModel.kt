package com.sejongunivclub.calendar.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sejongunivclub.calendar.CalendarState
import com.sejongunivclub.calendar.util.CalendarUtil
import com.sejongunivclub.coroutine.DefaultDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _uiState = MutableStateFlow<CalendarState>(CalendarState.Loading)
    val uiState get() = _uiState.asStateFlow()

    val selectedIndex = mutableStateOf(Triple(-1, -1, -1))

    fun loadCalendar(pageCount: Int) = viewModelScope.launch(dispatcher) {
        _uiState.value = CalendarState.Success(CalendarUtil.makeCalenderPage(pageCount))
    }

}