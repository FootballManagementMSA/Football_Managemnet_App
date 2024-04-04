package com.example.feature_mypage.presentation.ui_component

import androidx.annotation.ArrayRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.model.StudentUiModel
import com.example.ui_component.R
import com.example.ui_component.VerticalSpacer

@Composable
fun MyPageModifyInfoView(modifier: Modifier = Modifier, studentData: MutableState<StudentUiModel>) {
    Column(
        modifier = modifier
            .padding(start = 40.dp, end = 40.dp, top = 10.dp)
    ) {
        MyPageInfoBar(
            placeholder = studentData.value.name,
            infoText = "이름(닉네임)",
            isModifyEnable = false
        )
        MyPageInfoBar(
            placeholder = studentData.value.age.toString(),
            infoText = "나이 (출생년도)",
            isModifyEnable = false
        )
        MyPageInfoBar(placeholder = "180cm", infoText = "키 (cm)", isModifyEnable = false)
        MyPageDropDownInfoBar(
            placeholder = "남성",
            infoText = "성별",
            isModifyEnable = true,
            menuList = getArrayFromResource(arrayResId = R.array.sex),
            onFootValueSelected = {}
        )
        MyPagePositionDropDownInfoBar(
            placeholder = studentData.value.position,
            infoText = "주포메이션",
            isModifyEnable = true,
            menuList = getArrayFromResource(arrayResId = R.array.positions),
            onPositionValueSelected = { position ->
                studentData.value.position = position
            }
        )
        MyPageDropDownInfoBar(
            placeholder = studentData.value.foot,
            infoText = "주발",
            isModifyEnable = true,
            menuList = getArrayFromResource(arrayResId = R.array.foot),
            onFootValueSelected = { selectedFoot ->
                studentData.value.foot = selectedFoot
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MyPageInfoBar(
    modifier: Modifier = Modifier,
    placeholder: String,
    infoText: String,
    isModifyEnable: Boolean
) {
    val text = remember {
        mutableStateOf("")
    }
    val textColor = Color.Gray
    Column(
        modifier = Modifier
    ) {
        Text(text = infoText, style = TextStyle(color = Color.White))
        VerticalSpacer(value = 7)
        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            enabled = isModifyEnable,
            value = text.value,
            onValueChange = { text.value = it },
            singleLine = true,
            placeholder = {
                Text(text = placeholder, color = textColor)
            },
            textStyle = TextStyle(color = textColor),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.Gray,
                disabledBorderColor = Color.Gray,
            ),
            trailingIcon = {
                if (isModifyEnable) {
                    Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "")
                }
            }
        )
        VerticalSpacer(value = 25)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MyPageDropDownInfoBar(
    modifier: Modifier = Modifier,
    placeholder: String,
    infoText: String,
    isModifyEnable: Boolean,
    menuList: Array<String>,
    onFootValueSelected: (String) -> Unit,
) {
    val text = remember {
        mutableStateOf("")
    }
    val textColor = Color.Gray
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
    ) {
        Text(text = infoText, style = TextStyle(color = Color.White))
        VerticalSpacer(value = 7)
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            OutlinedTextField(
                modifier = modifier
                    .fillMaxWidth()
                    .menuAnchor(),
                enabled = isModifyEnable,
                value = text.value,
                onValueChange = { text.value = it },
                singleLine = true,
                readOnly = true,
                placeholder = {
                    Text(text = placeholder, color = textColor)
                },
                textStyle = TextStyle(color = textColor),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.Gray,
                    disabledBorderColor = Color.Gray,
                ),
                trailingIcon = {
                    if (isModifyEnable) {
                        DropDownIcon(expanded = expanded)
                    }
                }
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                menuList.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            text.value = item
                            expanded = false
                            onFootValueSelected(item)
                        }
                    )
                }
            }

        }
        VerticalSpacer(value = 25)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MyPagePositionDropDownInfoBar(
    modifier: Modifier = Modifier,
    placeholder: String,
    infoText: String,
    isModifyEnable: Boolean,
    menuList: Array<String>,
    onPositionValueSelected: (String) -> Unit,
) {
    val text = remember {
        mutableStateOf("")
    }
    val textColor = Color.Gray
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
    ) {
        Text(text = infoText, style = TextStyle(color = Color.White))
        VerticalSpacer(value = 7)
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            OutlinedTextField(
                modifier = modifier
                    .fillMaxWidth()
                    .menuAnchor(),
                enabled = isModifyEnable,
                value = text.value,
                onValueChange = { text.value = it },
                singleLine = true,
                readOnly = true,
                placeholder = {
                    Text(text = placeholder, color = textColor)
                },
                textStyle = TextStyle(color = textColor),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.Gray,
                    disabledBorderColor = Color.Gray,
                ),
                trailingIcon = {
                    if (isModifyEnable) {
                        DropDownIcon(expanded = expanded)
                    }
                }
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                menuList.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            text.value = item
                            expanded = false
                            onPositionValueSelected(item)
                        }
                    )
                }
            }

        }
        VerticalSpacer(value = 25)
    }
}

@ExperimentalMaterial3Api
@Composable
fun DropDownIcon(expanded: Boolean) {
    Icon(
        Icons.Default.KeyboardArrowDown,
        null,
        Modifier.rotate(if (expanded) 180f else 0f)
    )
}

@Composable
private fun getArrayFromResource(@ArrayRes arrayResId: Int): Array<String> {
    return LocalContext.current.resources.getStringArray(arrayResId)
}

@Preview
@Composable
fun MyPageModifyInfoViewPreview() {
    MyPageModifyInfoView(studentData = mutableStateOf(StudentUiModel("", 0, 0, "", "", "", 0)))
}