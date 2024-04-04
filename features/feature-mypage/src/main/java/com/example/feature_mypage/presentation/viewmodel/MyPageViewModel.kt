package com.example.feature_mypage.presentation.viewmodel

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.net.Uri
import android.provider.MediaStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.ResultState.BaseResult
import com.example.core.model.ModifyUserInfoModel
import com.example.core.model.StudentUiModel
import com.example.feature_mypage.domain.usecase.ClearDataStoreUseCase
import com.example.feature_mypage.domain.usecase.GetUserInfoUseCase
import com.example.feature_mypage.domain.usecase.LoadMyPageDataUseCase
import com.example.feature_mypage.domain.usecase.ModifyUserInfoUseCase
import com.example.feature_mypage.domain.usecase.SignOutUseCase
import com.example.feature_mypage.presentation.UserInfoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val contentResolver: ContentResolver,
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val clearDataStoreUseCase: ClearDataStoreUseCase,
    private val modifyUserInfoUseCase: ModifyUserInfoUseCase,
    private val signOutUseCase: SignOutUseCase,
    private val loadMyPageDataUseCase: LoadMyPageDataUseCase
) : ViewModel() {
    private val _selectedImageUri = MutableStateFlow<Uri?>(null)
    val selectedImageUri: StateFlow<Uri?> = _selectedImageUri

    private val _uiState = MutableStateFlow<UserInfoState>(UserInfoState.Loading)
    val uiState get() = _uiState

    private val _modifyUserInfoResult = MutableSharedFlow<BaseResult>(replay = 0)
    val modifyUserInfoResult: SharedFlow<BaseResult> = _modifyUserInfoResult.asSharedFlow()

    private val _studentData =
        MutableStateFlow<StudentUiModel>(StudentUiModel("", 0, 0, "", "", "", 0))
    val studentData: StateFlow<StudentUiModel> = _studentData

    private val ioDispatcher = Dispatchers.IO

    init {
        loadUserInfo()
    }

    fun updateSelectedImageUri(uri: Uri?) {
        _selectedImageUri.value = uri
    }

    fun loadUserInfo() {
        viewModelScope.launch {
            _uiState.value = UserInfoState.Success(getUserInfoUseCase())
            _studentData.value = loadMyPageDataUseCase()
        }
    }

    fun clearDataStore() {
        viewModelScope.launch {
            clearDataStoreUseCase()
        }
    }

    @SuppressLint("Recycle", "Range")
    fun modifyUserInfo() {
        viewModelScope.launch(ioDispatcher) {
            if (_selectedImageUri.value != null) {
                val cursor =
                    contentResolver.query(_selectedImageUri.value!!, null, null, null, null)
                cursor?.moveToNext()
                val path =
                    cursor?.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA))
                val file = path?.let { File(it) }
                file?.let {
                    val result = modifyUserInfoUseCase(
                        ModifyUserInfoModel(
                            _studentData.value.name,
                            _studentData.value.age,
                            190,
                            "남성",
                            _studentData.value.position,
                            _studentData.value.foot,
                            it
                        )
                    )
                    _modifyUserInfoResult.emit(result)
                }
            }
        }
    }

    fun signOut() {
        viewModelScope.launch(ioDispatcher) {
            signOutUseCase()
        }
    }
}