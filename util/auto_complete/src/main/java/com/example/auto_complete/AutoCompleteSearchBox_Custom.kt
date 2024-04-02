package com.example.auto_complete
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.model.LocationInfo
import com.example.core.model.Map
import java.nio.file.LinkOption

@Composable
fun AutoCompleteSearchBox_Custom(
    modifier: Modifier = Modifier,
    placeholder: String,
    viewModel: AutoCompleteViewModel = hiltViewModel(),
    onSelect: (LocationInfo) -> Unit = {},
    content: @Composable (LocationInfo) -> Unit
) {
    val expanded = viewModel.expandedMap.collectAsState()
    val searchValue = viewModel.searchMapValue.collectAsState()
    val items = viewModel.itemsMap.collectAsState()
    Column(
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth()
            .testTag("AutoComplete")
    ) {
        AutoCompleteTextField_Custom(
            searchValue = searchValue,
            expanded = { viewModel.updateExpandedMapValue(!expanded.value) },
            placeholder = placeholder,
            onIconClick = { viewModel.fetchMapItems(searchValue.value)}
        ) {
            viewModel.updateSearchMapValue(it)
        }

        SearchDropdown_Custom(
            expanded = expanded,
            items = items.value,
            onSearchValueSelect = {
                onSelect(it)
                viewModel.updateSearchMapValue(it.address)
                viewModel.updateExpandedMapValue(false)
            },
        ) {
            content(it)
        }
    }
}

@Composable
fun AutoCompleteTextField_Custom(
    searchValue: State<String>,
    expanded: () -> Unit,
    placeholder: String,
    onIconClick: () -> Unit,
    onValueChange: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current

    Row(modifier = Modifier.fillMaxWidth()) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(10.dp)
                )
                .testTag("AutoCompleteTextField"),
            value = searchValue.value,
            onValueChange = onValueChange,
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 16.sp
            ),
            placeholder = { Text(text = placeholder) },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    expanded()
                    focusManager.clearFocus()
                }
            ),
            trailingIcon = {
                IconButton(onClick = {
                    onIconClick()
                    expanded()
                    focusManager.clearFocus()
                }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                }
            },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                cursorColor = Color.Black
            )
        )
    }
}

@Composable
fun SearchDropdown_Custom(
    expanded: State<Boolean>,
    items: List<LocationInfo>,
    onSearchValueSelect: (LocationInfo) -> Unit,
    content: @Composable (LocationInfo) -> Unit
) {
    AnimatedVisibility(visible = expanded.value) {
        Card(
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .heightIn(max = 450.dp)
                    .testTag("SearchDropdown"),
            ) {
                items(items) {
                    SearchItems_Custom(item = it, onSearchValueSelect = onSearchValueSelect) {
                        content(it)
                    }
                }
            }
        }
    }
}

@Composable
fun SearchItems_Custom(item: LocationInfo, onSearchValueSelect: (LocationInfo) -> Unit, content: @Composable () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onSearchValueSelect(item)
            }
            .padding(10.dp)
            .testTag("SearchItem")
    ) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun AutoCompletePreview1() {

}