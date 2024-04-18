package com.sejongunivclub.feature_mypage.presentation

import com.sejongunivclub.core.model.MyPageUserInfoUiModel

sealed class UserInfoState {
    data class Success(val data: MyPageUserInfoUiModel) : UserInfoState()
    data object Loading : UserInfoState()
}
