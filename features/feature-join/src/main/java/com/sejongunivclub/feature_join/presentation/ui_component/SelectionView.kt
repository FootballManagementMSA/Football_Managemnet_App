package com.sejongunivclub.feature_join.presentation.ui_component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SelectionView(content: List<Pair<String, String>>, onSelect: (String) -> Unit) {
    var selectedIndex by remember { mutableStateOf(-1) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        content.forEachIndexed { index, (desc, position) ->
            SelectProfileButton(
                isSelected = selectedIndex == index,
                onClick = {
                    if (selectedIndex != index) {
                        selectedIndex = index
                        onSelect(content[selectedIndex].second)
                    }
                },
                contentDescription = desc,
                position = position,
                modifier = Modifier
                    .weight(1f)
                    .height(30.dp)
            )
        }
    }
}