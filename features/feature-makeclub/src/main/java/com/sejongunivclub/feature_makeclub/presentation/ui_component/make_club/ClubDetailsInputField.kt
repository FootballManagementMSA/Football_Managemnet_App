package com.sejongunivclub.feature_makeclub.presentation.ui_component.make_club

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sejongunivclub.ui_component.VerticalSpacer
import com.sejongunivclub.ui_component.values.darkGray
import com.sejongunivclub.ui_component.values.subTheme
import com.sejongunivclub.ui_component.values.tinyFont

@Composable
fun ClubDetailsInputField(
    state: State<String>,
    onValueChange: (String) -> Unit
) {
    val isFocused = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(subTheme)
    ) {
        BasicTextField(
            value = state.value,
            onValueChange = {
                onValueChange(it)
                isFocused.value = it.isNotEmpty()
            },
            textStyle = TextStyle(
                color = darkGray,
                textAlign = TextAlign.Center
            ),
            singleLine = false,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        )

        if (!isFocused.value && state.value.isEmpty()) {
            Text(
                text = "구단의 세부적인 내용을 입력해주세요. (100자 이내)",
                color = Color.Gray,
                fontSize = tinyFont,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .align(Alignment.CenterStart)
            )
        }
    }
    VerticalSpacer(value = 20)
}

@Preview
@Composable
fun ClubDetailsInputFieldPreview() {
    val state = remember {
        mutableStateOf("preview")
    }
    ClubNameInputField(state = state, onValueChange = {} )
}