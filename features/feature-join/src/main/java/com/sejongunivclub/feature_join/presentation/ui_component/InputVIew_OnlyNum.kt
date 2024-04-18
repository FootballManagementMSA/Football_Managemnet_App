package com.sejongunivclub.feature_join.presentation.ui_component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun InputView_OnlyNum(
    state: State<String>,
    label: String,
    placeholder: String,
    onValueChanges: (String) -> Unit
) {
    OutlinedTextField(
        value = state.value,
        label = { Text(text = label) },
        placeholder = { Text(text = placeholder) },
        onValueChange = {
            onValueChanges(it)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number, 
            imeAction = ImeAction.Done
        ),
        modifier = Modifier.fillMaxWidth(),
        textStyle = MaterialTheme.typography.bodyLarge
    )
}