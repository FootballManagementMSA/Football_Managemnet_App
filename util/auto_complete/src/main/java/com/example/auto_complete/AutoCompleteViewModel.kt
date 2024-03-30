package com.example.auto_complete

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.model.ClubInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AutoCompleteViewModel @Inject constructor(
    private val autoCompleteSearchClubUseCase: AutoCompleteSearchClubUseCase
): ViewModel(){
    private val _searchValue = MutableStateFlow("")
    val searchValue: StateFlow<String> = _searchValue

    private val _expanded = MutableStateFlow(false)
    val expanded: StateFlow<Boolean> = _expanded

    private val _items = MutableStateFlow<List<ClubInfo>>(emptyList())
    val items: StateFlow<List<ClubInfo>> = _items

    fun updateExpandedValue(boolean: Boolean) {
        _expanded.value = boolean
    }

    fun updateSearchValue(value: String) {
        _searchValue.value = value
    }

    fun fetchItems(query: String) {
        viewModelScope.launch {
            _items.value = autoCompleteSearchClubUseCase(query)
            _expanded.value = true
        }
    }
}