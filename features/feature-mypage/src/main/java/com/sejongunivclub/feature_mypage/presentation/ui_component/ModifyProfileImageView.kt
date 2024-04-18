package com.sejongunivclub.feature_mypage.presentation.ui_component

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sejongunivclub.ui_component.template.DefaultEmblemSelectIconView

@Composable
fun ModifyProfileImageView(
    modifier: Modifier = Modifier,
    state: State<Uri?>,
    onBack: () -> Unit,
    onDesroy: () -> Unit,
    onSelect: (Uri?) -> Unit
) {
    Column(modifier.padding(top = 40.dp)) {
        MyPageTopView(
            modifier = Modifier
                .requiredHeightIn(min = 100.dp)
                .weight(4f),
            title = "마이페이지",
            onBack = onBack,
            onDesroy = onDesroy
        )
        DefaultEmblemSelectIconView(
            modifier = Modifier
                .requiredHeightIn(min = 100.dp)
                .weight(5f)
                .padding(top = 30.dp)
                .fillMaxWidth(),
            state = state,
            onSelect = onSelect
        )
    }
}

@Preview
@Composable
fun ModifyProfileImageViewPreview() {
    val state = remember { mutableStateOf<Uri?>(null) }
    ModifyProfileImageView(state = state, onDesroy = {}, onBack = {}) {}
}