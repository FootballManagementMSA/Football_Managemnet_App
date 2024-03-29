package com.example.presentation.ui_component


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.core.model.MainSchedule
import com.example.core.model.Team
import com.example.ui_component.R
import com.example.ui_component.VerticalSpacer
import com.example.ui_component.buttons.RoundedIconButton
import com.example.ui_component.template.DefaultItem
import com.example.ui_component.template.DefaultListView
import com.example.ui_component.values.darkGray
import com.example.ui_component.values.horizontalGradation
import com.example.ui_component.values.mainTheme
import com.example.ui_component.values.middleFont
import com.example.ui_component.values.smallFont
import com.example.ui_component.values.smallIcon
import com.example.ui_component.values.tinyFont
import com.example.ui_component.values.veryBigFont
import com.example.ui_component.values.veryTinyFont
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun ScheduleView(
    modifier: Modifier = Modifier,
    currentSchedule: State<List<MainSchedule?>>,
    onClick: () -> Unit
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    val tabs = listOf("일정")

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        ScrollableTabRow(
            modifier = Modifier
                .wrapContentSize()
                .padding(horizontal = 20.dp),
            edgePadding = 0.dp,
            selectedTabIndex = selectedTabIndex,
            indicator = { tabPositions ->

            },
            divider = {
                Box(
                    Modifier
                        .height(7.dp)
                        .background(horizontalGradation)
                ) {

                }
            },
            containerColor = mainTheme
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = {
                        Text(
                            text = title,
                            color = Color.White,
                            fontSize = smallFont,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    selected = index == selectedTabIndex,
                    onClick = {
                        selectedTabIndex = index
                    }
                )
            }
        }
        when (selectedTabIndex) {
            0 -> {
                if (currentSchedule.value.isEmpty()) {
                    EmptyScheduleContent {
                        onClick()
                    }
                } else {
                    DefaultListView(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp),
                        listName = "현재 예약된 일정",
                        buttonName = "일정 생성하기",
                        showButton = true,
                        listIcon = Icons.Default.DateRange,
                        onClick = { }) {
                        items(currentSchedule.value) { schedule ->
                            DefaultItem(
                                modifier = Modifier.padding(bottom = 12.dp),
                                radius = 8.dp
                            ) {
                                CompositionLocalProvider(
                                    LocalTextStyle provides TextStyle(color = Color.White)
                                ) {
                                    ScheduleItem(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(15.dp), schedule = schedule!!
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ScheduleItem(modifier: Modifier = Modifier, schedule: MainSchedule) {
    Column(
        modifier
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = schedule.place)
            Image(
                modifier = Modifier.size(smallIcon),
                painter = painterResource(id = R.drawable.pin_button),
                contentDescription = "pin"
            )
        }
        Text(
            text = schedule.startTime.toString(),
            fontSize = veryBigFont,
            fontWeight = FontWeight.Bold
        )
        MatchInfo(
            modifier = Modifier
                .padding(top = 5.dp)
                .fillMaxWidth()
                .weight(1f), schedule
        )
    }
}

@Composable
private fun MatchInfo(modifier: Modifier = Modifier, schedule: MainSchedule) {
    Row(
        modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Emblem(schedule.homeTeam.emblem)
        TeamInfo(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(), schedule.homeTeam.name
        )
        Text(text = "VS", fontSize = middleFont, fontWeight = FontWeight.Bold)
        TeamInfo(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(), schedule.awayTeam.name
        )
        Emblem(schedule.awayTeam.emblem)
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = ""
        )
    }
}

@Composable
private fun Emblem(emblem: String) {
    AsyncImage(
        model = emblem,
        contentDescription = null,
        placeholder = painterResource(R.drawable.league_icon),
        modifier = Modifier
            .aspectRatio(1f)
            .fillMaxHeight()
            .clip(CircleShape),
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun TeamInfo(modifier: Modifier = Modifier, team: String) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = team, fontSize = tinyFont)
        Text(
            text = "this is $team",
            fontSize = veryTinyFont
        )
    }
}

@Composable
fun EmptyScheduleContent(onClick: () -> Unit) {
    val config = LocalConfiguration.current
    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(darkGray)
                .height(1.dp)
        )
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .wrapContentSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "예정된 일정이 없습니다. \n 지금 일정을 만들어 보세요!",
                textAlign = TextAlign.Center,
                color = darkGray,
                fontWeight = FontWeight.Bold
            )
            VerticalSpacer(value = 14)
            RoundedIconButton(
                modifier = Modifier.width(config.screenWidthDp.dp / 2),
                icon = Icons.Default.DateRange,
                content = "일정 생성"
            ) {
                onClick()
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun ScheduleItemPreview() {

    ScheduleItem(
        modifier = Modifier
            .height(150.dp)
            .padding(15.dp),
        schedule = generateDummyData(1)[0]
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun ScheduleViewPreview() {
    val state = remember {
        mutableStateOf(generateDummyData(5))
    }
    ScheduleView(currentSchedule = state) {

    }
}


@Composable
@Preview
fun EmptyScheduleContentPreview() {
    EmptyScheduleContent {

    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun generateDummyData(count: Int): List<MainSchedule> {
    val dummyLocations = listOf("Stadium A", "Stadium B", "Stadium C", "Stadium D", "Stadium E")
    val dummyDates = listOf("2022-01-01", "2022-02-15", "2022-03-10", "2022-04-20", "2022-05-05")
    val dummyTeams = listOf(Team("test", ""), Team("test2", ""), Team("test3", ""))

    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    return (1..count).map {
        MainSchedule(
            place = dummyLocations.random(),
            startTime = LocalDateTime.parse(dummyDates.random(), formatter),
            homeTeam = dummyTeams.random(),
            awayTeam = dummyTeams.random()
        )
    }
}