package com.sejongunivclub.ui_component.template

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sejongunivclub.ui_component.HorizontalSpacer
import com.sejongunivclub.ui_component.VerticalSpacer
import com.sejongunivclub.ui_component.values.largeIcon
import com.sejongunivclub.ui_component.values.smallFont
import com.sejongunivclub.ui_component.values.verticalGradation

@Composable
fun DefaultListView(
    modifier: Modifier = Modifier,
    themeColor : Color = Color.White,
    listName: String,
    listIcon: ImageVector,
    buttonName: String = "",
    showButton: Boolean = false,
    onClick: () -> Unit,
    listContent: LazyListScope.() -> Unit
) {
    Column(modifier.fillMaxHeight()) {
        Row {
            CompositionLocalProvider(
                LocalContentColor provides themeColor
            ) {
                Row {
                    Icon(
                        modifier = Modifier.size(largeIcon),
                        imageVector = listIcon,
                        contentDescription = "Icon"
                    )
                    HorizontalSpacer(value = 3)
                    Text(text = listName, fontSize = smallFont, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.weight(1f))
                if (showButton) {
                    Row(Modifier.clickable { onClick() }) {
                        Text(text = buttonName, fontSize = smallFont, fontWeight = FontWeight.Bold)
                        HorizontalSpacer(value = 3)
                        Icon(
                            modifier = Modifier.size(largeIcon),
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "Icon"
                        )
                    }
                }
            }
        }
        VerticalSpacer(value = 15)
        LazyColumn(
            modifier
                .fillMaxSize(), content = listContent
        )
    }
}

@Composable
@Preview
fun DefaultListViewPreView() {
    val state = remember {
        mutableStateOf(listOf(1, 2, 3, 4, 5))
    }
    DefaultListView(
        modifier = Modifier.fillMaxSize(),
        listName = "현재 예약된 일정",
        buttonName = "일정 생성하기",
        showButton = true,
        listIcon = Icons.Default.DateRange,
        onClick = { }) {
        items(state.value) {
            DefaultItem(
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .border(
                        width = 8.dp,
                        brush = verticalGradation,
                        shape = RoundedCornerShape(16.dp)
                    ),
                color = Color.White,
                radius = 16.dp
            ) {
                Column(Modifier.padding(20.dp)) {
                    Text(text = it.toString())
                }
            }
        }
    }
}