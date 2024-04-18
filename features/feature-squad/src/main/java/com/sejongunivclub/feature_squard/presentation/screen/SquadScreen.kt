package com.sejongunivclub.feature_squard.presentation.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sejongunivclub.core.model.LocalScreen
import com.sejongunivclub.core.model.User
import com.sejongunivclub.core.model.UserData
import com.sejongunivclub.feature_squard.presentation.SquadState
import com.sejongunivclub.feature_squard.presentation.ui_component.CandidateView
import com.sejongunivclub.feature_squard.presentation.ui_component.DraggableMember
import com.sejongunivclub.feature_squard.presentation.viewmodel.SquadViewModel
import com.sejongunivclub.ui_component.template.DefaultBottomSheet
import com.sejongunivclub.ui_component.R
import com.sejongunivclub.ui_component.VerticalSpacer
import com.sejongunivclub.ui_component.values.mainTheme

@Composable
fun SquadScreen(viewModel: SquadViewModel = hiltViewModel()) {
    val state by viewModel.uiState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.loadPreset(0L, 0L) // teamId, scheduleId 기입해야함
    }
    when (state) {
        is SquadState.Loading -> {
            CircularProgressIndicator()
        }

        is SquadState.Success -> {
            SquadContent(
                onLoad = { (state as SquadState.Success).data },
                onSet = { position ->
                    Log.e("callback", "$position")
                    viewModel.savePosition(0L, 0L, UserData(position)) // teamId, scheduleId 기입해야함
                })
        }
    }
}


@Composable
private fun SquadContent(
    onLoad: () -> UserData,
    onSet: (List<User>) -> Unit
) {
    val positions = remember { onLoad().users.toMutableList() }
    val showSheet = remember { mutableStateOf(false) }
    val config = LocalConfiguration.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(mainTheme)
            .padding(10.dp)
    ) {
        Column(Modifier.fillMaxSize()) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                painter = painterResource(id = R.drawable.field),
                contentDescription = "field"
            )
            VerticalSpacer(value = 10)
            CandidateView()
        }
        positions.forEachIndexed { index, _ ->
            DraggableMember(
                onLoad = {
                    onLoad().users[index] to LocalScreen(
                        config.screenWidthDp.toDouble(),
                        config.screenHeightDp.toDouble()
                    )
                },
                onDrag = { newPosition ->
                    positions[index] = newPosition
                },
                onSet = {
                    showSheet.value = true
                })
        }
        Button(
            modifier = Modifier.align(Alignment.TopEnd),
            onClick = { onSet(positions) }) {
            Text(text = "save preset")
        }
    }
    if (showSheet.value) {
        DefaultBottomSheet(onDismiss = { showSheet.value = false }) {
            Box(Modifier.fillMaxHeight()) {

            }

        }
    }
}

@Composable
@Preview
fun SquadContentPreview() {
}