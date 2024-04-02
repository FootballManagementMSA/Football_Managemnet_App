package com.example.auto_complete

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.model.ClubInfo
import com.example.core.model.LocationInfo
import com.example.core.model.Map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AutoCompleteViewModel @Inject constructor(
    private val autoCompleteSearchClubUseCase: AutoCompleteSearchClubUseCase,
    private val autoCompleteMapUseCase: AutoCompleteMapUseCase

): ViewModel(){
    private val _searchValue = MutableStateFlow("")
    val searchValue: StateFlow<String> = _searchValue

    private val _expanded = MutableStateFlow(false)
    val expanded: StateFlow<Boolean> = _expanded

    private val _items = MutableStateFlow<List<ClubInfo>>(emptyList())
    val items: StateFlow<List<ClubInfo>> = _items

    private val _searchMapValue = MutableStateFlow("")
    val searchMapValue: StateFlow<String> = _searchMapValue

    private val _expandedMap = MutableStateFlow(false)
    val expandedMap: StateFlow<Boolean> = _expandedMap

    private val _itemsMap = MutableStateFlow<List<LocationInfo>>(emptyList())
    val itemsMap: StateFlow<List<LocationInfo>> = _itemsMap





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


    fun updateExpandedMapValue(boolean: Boolean) {
        _expandedMap.value = boolean
    }

    fun updateSearchMapValue(value: String) {
        _searchMapValue.value = value
    }

    fun fetchMapItems(query: String) {
        viewModelScope.launch {
            _itemsMap.value = autoCompleteMapUseCase(query)
            _expandedMap.value = true
        }
    }








}