package com.example.feature_schedule.presentation.view

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.auto_complete.AutoCompleteSearchBox
import com.example.auto_complete.AutoCompleteSearchBox_Custom
import com.example.calendar.Calendar
import com.example.core.model.ClubInfo
import com.example.core.model.ClubSchedule
import com.example.core.model.LocationInfo
import com.example.core.model.Map
import com.example.ui_component.HorizontalSpacer
import com.example.ui_component.R
import com.example.ui_component.buttons.CustomGradientButton
import com.example.ui_component.template.DefaultBottomSheet
import com.example.ui_component.template.DefaultEmblemSelectIconView
import com.example.ui_component.values.emblem
import com.example.ui_component.values.gradientColorsList
import com.example.ui_component.values.mainTheme
import com.example.ui_component.values.middleFont
import com.example.ui_component.values.tinyFont
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MakeScheduleScreen(
    onMake: (ClubSchedule) -> Unit
) {
    val myUri = remember { mutableStateOf<Uri?>(null) }
    val title = remember { mutableStateOf("") }
    val memo = remember { mutableStateOf("") }
    val startDate = remember { mutableStateOf("") }
    val endDate = remember { mutableStateOf("") }
    val location = remember { mutableStateOf("") }
    val showCalendar = remember { mutableStateOf(false) }
    val showAutoComplete = remember { mutableStateOf(false) }
    val showAutoCompleteCustom = remember { mutableStateOf(false) }
    val setStart = remember { mutableStateOf(false) }
    val selectedClub = remember { mutableStateOf<ClubInfo?>(null) }
    val selectedMap = remember { mutableStateOf<LocationInfo?>(null) }

    if (showCalendar.value) {
        CalendarSheet(showCalendar, setStart, startDate, endDate)
    }
    if (showAutoComplete.value) {
        AutoCompleteSheet(showAutoComplete = showAutoComplete) {
            selectedClub.value = it
            showAutoComplete.value = false
        }
    }

    if (showAutoCompleteCustom.value) {
        AutoCompleteSheet1(
            showAutoComplete = showAutoCompleteCustom,
            onSelect = { selectedLocation ->
                selectedMap.value = selectedLocation
                showAutoCompleteCustom.value = false
            },
            location = location
        )
    }

    Column(
        Modifier
            .background(mainTheme)
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Spacer(modifier = Modifier.weight(1f))
        ScheduleTitleView(myUri, title)
        Spacer(modifier = Modifier.weight(0.4f))
        ScheduleMemoView(memo)
        Spacer(modifier = Modifier.weight(0.4f))
        ScheduleDateView(startDate, showCalendar, setStart, endDate)
        Spacer(modifier = Modifier.weight(0.4f))
        ScheduleLocationView(location, showAutoCompleteCustom)
        Spacer(modifier = Modifier.weight(0.4f))
        Text("상대팀 설정", color = Color.Gray)
        SetTeamView(myUri, showAutoComplete, selectedClub)
        Spacer(modifier = Modifier.weight(1f))
        CustomGradientButton(
            gradientColors = gradientColorsList,
            cornerRadius = 8.dp,
            buttonText = "수락하기",
            roundedCornerShape = RoundedCornerShape(8.dp)
        ) {
            onMake(
                ClubSchedule(
                    title = title.value,
                    memo = memo.value,
                    startTime = parseToLocalDateTime(startDate.value),
                    endTime = parseToLocalDateTime(endDate.value),
                    place = location.value,
                    awayTeamId = selectedClub.value?.teamId?.toLong() ?: 0L, // 자동완성 시트
                    longitude = selectedMap.value?.mapx?.toDouble() ?: 0.0,
                    latitude = selectedMap.value?.mapy?.toDouble() ?: 0.0
                )
            )
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

fun parseToLocalDateTime(dateString: String): LocalDateTime {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    return LocalDateTime.parse(dateString, formatter)
}

@Composable
private fun SetTeamView(
    myUri: MutableState<Uri?>,
    showAutoComplete: MutableState<Boolean>,
    selectedClubInfo: MutableState<ClubInfo?>
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        DefaultEmblemSelectIconView(
            modifier = Modifier
                .clip(CircleShape)
                .background(emblem)
                .size(40.dp)
                .padding(top = 5.dp),
            state = myUri,
            defaultIcon = R.drawable.league_icon
        ) {

        }
        Text(
            modifier = Modifier.padding(horizontal = 10.dp),
            text = "vs",
            color = Color.Gray,
            fontSize = middleFont
        )
        AsyncImage(
            modifier = Modifier
                .clip(CircleShape)
                .background(emblem)
                .size(40.dp)
                .padding(top = 5.dp)
                .clickable {
                    showAutoComplete.value = true
                }, model = selectedClubInfo.value?.emblem, contentDescription = ""
        )
    }
}

@Composable
private fun ScheduleLocationView(
    location: MutableState<String>,
    showAutoComplete: MutableState<Boolean>,
) {
    Text("일정 장소", color = Color.Gray)

    InputView(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                showAutoComplete.value = true

            },

        text = { location.value },
        onChange = { location.value = it }, placeholder = "일정 장소를 입력해주세요.",
        leadingIcon = {
            Icon(
                modifier = Modifier.clickable { showAutoComplete.value = true },
                imageVector = Icons.Default.LocationOn,
                contentDescription = ""
            )
        }
    )
}

@Composable
private fun AutoCompleteSheet(
    showAutoComplete: MutableState<Boolean>,
    onSelect: (ClubInfo) -> Unit,

    ) {
    DefaultBottomSheet(
        modifier = Modifier.fillMaxHeight(0.6f),
        onDismiss = { showAutoComplete.value = false }) {
        AutoCompleteSearchBox(
            placeholder = "상대 구단을 검색해주세요.",
            onSelect = onSelect
        ) {
            Text(text = it.teamName.toString())
            HorizontalSpacer(value = 5)
            Text(fontSize = 10.sp, text = "구단 고유 코드 : ${it.uniqueNum}")
        }
    }
}

@Composable
fun ClickableNavItem(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(4.dp)
    ) {
        Text(
            text = "네이버 지도에서 찾아보기",
            color = Color.Blue,
            fontSize = tinyFont,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(horizontal = 2.dp)
                .align(Alignment.CenterStart)
                .padding(start = 20.dp)

        )
    }
}

@Composable
private fun AutoCompleteSheet1(
    showAutoComplete: MutableState<Boolean>,
    onSelect: (LocationInfo) -> Unit,
    location: MutableState<String> // 추가된 부분
) {
    val context = LocalContext.current

    DefaultBottomSheet(
        modifier = Modifier.fillMaxHeight(0.6f),
        onDismiss = { showAutoComplete.value = false }
    ) {
        Column {


            AutoCompleteSearchBox_Custom(
                placeholder = "일정의 위치를 검색해주세요.",
                onSelect = {
                    onSelect(it)
                    // 선택한 위치를 ScheduleLocationView로 전달
                    location.value = it.address
                    showAutoComplete.value = false // 자동 완성 창 닫기
                }
            ) {
                Text(text = it.title)
                HorizontalSpacer(value = 5)
                Text(fontSize = 10.sp, text = it.address)
            }

            ClickableNavItem(
                onClick = {
                    val naverMapUri = Uri.parse("https://map.naver.com/")
                    val intent = Intent(Intent.ACTION_VIEW, naverMapUri)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    context.startActivity(intent)
                }
            )
        }
    }
}


@Composable
private fun ScheduleDateView(
    startDate: MutableState<String>,
    showCalendar: MutableState<Boolean>,
    setStart: MutableState<Boolean>,
    endDate: MutableState<String>
) {
    Text(text = "일정 날짜", color = Color.Gray)
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        InputView(
            modifier = Modifier
                .weight(1f),
            text = { startDate.value },
            onChange = { startDate.value = it }, placeholder = "시작 날짜를 입력해주세요.",
            leadingIcon = {
                Icon(
                    modifier = Modifier.clickable {
                        showCalendar.value = true
                        setStart.value = true
                    },
                    imageVector = Icons.Default.DateRange,
                    contentDescription = ""
                )
            }
        )
        Box(
            modifier = Modifier
                .width(10.dp)
                .height(1.dp)
                .background(Color.Gray)
        )
        InputView(
            modifier = Modifier
                .weight(1f),
            text = { endDate.value },
            onChange = { endDate.value = it }, placeholder = "종료 날짜를 입력해주세요.",

            leadingIcon = {
                Icon(
                    modifier = Modifier.clickable {
                        showCalendar.value = true
                    },
                    imageVector = Icons.Default.DateRange,
                    contentDescription = ""
                )
            }
        )
    }
}

@Composable
private fun ScheduleMemoView(memo: MutableState<String>) {
    Text("일정 메모", color = Color.Gray)
    InputView(
        modifier = Modifier
            .fillMaxWidth(),
        text = { memo.value },
        onChange = { memo.value = it }, placeholder = "메모를 입력해주세요."
    )
}

@Composable
private fun ScheduleTitleView(
    uri: MutableState<Uri?>,
    title: MutableState<String>
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "일정 제목", color = Color.Gray)
            DefaultEmblemSelectIconView(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(emblem)
                    .size(60.dp)
                    .padding(top = 5.dp),
                state = uri,
                defaultIcon = R.drawable.league_icon
            ) {

            }
        }
        InputView(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            text = { title.value },
            onChange = { title.value = it }, placeholder = "일정 제목을 입력해주세요."
        )
    }
}

@Composable
private fun CalendarSheet(
    showCalendar: MutableState<Boolean>,
    setStart: MutableState<Boolean>,
    startDate: MutableState<String>,
    endDate: MutableState<String>
) {
    DefaultBottomSheet(onDismiss = { showCalendar.value = false }) {
        Calendar(
            modifier = Modifier
                .padding(20.dp)
                .height(350.dp)
        ) {
            if (setStart.value) {
                startDate.value = formatDate(it.year, it.month, it.day)+ " 00:00:00"
                setStart.value = false
            } else {
                endDate.value = formatDate(it.year, it.month, it.day) + " 00:00:00"
            }
            showCalendar.value = false
        }
    }
}

fun formatDate(year: Int, month: Int, day: Int): String {
    val formattedMonth = month.toString().padStart(2, '0')
    val formattedDay = day.toString().padStart(2, '0')
    return "$year-$formattedMonth-$formattedDay"
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun MakeScheduleScreenPreview() {

}


@Composable
fun InputView(
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
    text: () -> String,
    onChange: (String) -> Unit,
    placeholder: String
) {
    TextField(
        modifier = modifier.height(50.dp),
        value = text(),
        leadingIcon = leadingIcon,
        onValueChange = onChange,
        placeholder = { Text(text = placeholder, fontSize = tinyFont) },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = mainTheme,
            focusedContainerColor = mainTheme,
        )
    )

}