package com.sejongunivclub.feature_makeclub.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sejongunivclub.feature_makeclub.presentation.ui_component.CompleteView
import com.sejongunivclub.ui_component.values.mainTheme

@Composable
fun CompleteClubMakingScreen(onNavigateToJoinClub: () -> Unit) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(color = mainTheme)
            .padding(40.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        CompleteView(buttonText = "확인") {
            onNavigateToJoinClub()
        }
    }
}

@Preview
@Composable
fun CompleteClubMakingScreenPreview() {
    CompleteClubMakingScreen(onNavigateToJoinClub = {})
}