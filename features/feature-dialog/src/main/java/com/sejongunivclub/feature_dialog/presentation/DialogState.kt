package com.sejongunivclub.feature_dialog.presentation

import com.sejongunivclub.core.model.UserInfo

sealed class DialogState {
    data object Loading : DialogState()
    data class Success(val data: List<UserInfo>) : DialogState()
}
