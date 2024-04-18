package com.sejongunivclub.feature_dialog.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.sejongunivclub.feature_dialog.presentation.DialogState
import com.sejongunivclub.feature_dialog.presentation.viewmodel.DialogViewModel

@Composable
fun DialogTestScreen(viewModel: DialogViewModel = hiltViewModel()) {
    val uiState = viewModel.dialogUiState.collectAsState()
    val isDialogOpen = remember { mutableStateListOf<Boolean>() }

    when (uiState.value) {
        is DialogState.Loading -> {
            CircularProgressIndicator()
        }

        is DialogState.Success -> {
            DialogTestContent(isOpen = isDialogOpen, dialogState = uiState.value as DialogState.Success)
        }
    }

}

@Composable
private fun DialogTestContent(isOpen: MutableList<Boolean>, dialogState: DialogState.Success) {
    val userInfos = remember {
        dialogState.data.toMutableList()
    }
    Column(modifier = Modifier.background(Color.White)) {
        Button(onClick = {
            isOpen.clear()
            isOpen.addAll(List(userInfos.size) { true })
        }) {
            Text(text = "모든 다이얼로그 보기")
        }

        userInfos.forEachIndexed { index, userInfo ->
            if (isOpen.getOrNull(index) == true) {
                ClubJoinCheckDialog(
                    userInfo = userInfo,
                    onDismiss = { isOpen[index] = false }
                )
            }
        }
    }
}

@Preview
@Composable
fun DialogTestScreenPreview() {
    DialogTestScreen()
}