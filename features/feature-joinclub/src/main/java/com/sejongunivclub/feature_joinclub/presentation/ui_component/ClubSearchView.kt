package com.sejongunivclub.feature_joinclub.presentation.ui_component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sejongunivclub.ui_component.VerticalSpacer
import com.sejongunivclub.ui_component.buttons.RoundedIconButton
import com.sejongunivclub.ui_component.values.hugeFont
import com.sejongunivclub.ui_component.values.tinyFont

@Composable
fun ClubSearchView(
    modifier: Modifier = Modifier,
    showSheet: () -> Unit,
    onSearchIconClick: (String) -> Unit,
    onNavigateToMakeClub: () -> Unit
) {
    Column(
        modifier
    ) {
        Box(Modifier.fillMaxWidth()) {
            Icon(
                modifier = Modifier.align(Alignment.CenterEnd),
                imageVector = Icons.Default.Menu,
                contentDescription = "",
                tint = Color.White
            )
        }
        Text(
            text = "구단을",
            fontWeight = FontWeight.Bold,
            fontSize = hugeFont,
            color = Color.White
        )
        Text(
            text = "검색해주세요",
            fontWeight = FontWeight.Bold,
            fontSize = hugeFont,
            color = Color.White
        )
        VerticalSpacer(value = 10)
        Text(
            text = "구단의 이름이나 코드로 검색 할 수 있어요.",
            fontWeight = FontWeight.Bold,
            fontSize = tinyFont,
            color = Color.White
        )
        Spacer(modifier = Modifier.weight(1f))
        ClubSearchBar(
            Modifier
                .fillMaxWidth()
        ) {
            onSearchIconClick(it)
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "찾는 구단이 없다면?\n지금 직접 구단을 만들어 보세요.",
                fontWeight = FontWeight.Bold,
                fontSize = tinyFont,
                color = Color.White
            )
            RoundedIconButton(
                modifier = Modifier.wrapContentSize(),
                icon = Icons.Default.DateRange,
                content = "구단 제작",
            ) {
                showSheet()
                onNavigateToMakeClub()
            }
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun ClubSearchBar(modifier: Modifier = Modifier, onIconClick: (String) -> Unit) {
    val text = remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        modifier = modifier,
        value = text.value,
        onValueChange = { text.value = it },
        singleLine = true,
        placeholder = {
            Text(text = "구단의 이름을 검색해주세요.")
        }, trailingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "",
                modifier = Modifier.clickable { onIconClick(text.value) })
        })
}

@Composable
@Preview
fun ClubSearchViewPreview() {
    val showSheet = remember {
        mutableStateOf(false)
    }
    ClubSearchView(
        Modifier
            .height(300.dp),
        showSheet = {},
        onSearchIconClick = {},
    ) {
    }
}