package com.example.feature_clubpage.presentation.ui_component
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Settings
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.model.ClubMember
import com.example.core.model.Schedule
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

@Composable
fun ScheduleView_Sample(modifier: Modifier = Modifier, currentSchedule: State<List<Schedule>>, currentClubMember:State<List<ClubMember>>) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    val tabs = listOf("일정","멤버","맴버신청")

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
                    EmptyScheduleContent()
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
                                            .padding(15.dp), schedule = schedule
                                    )
                                }
                            }
                        }
                    }
                }
            }
            1->{
                if (currentSchedule.value.isEmpty()) {
                    EmptyScheduleContent()
                } else {
                    DefaultListView(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp),
                        listName = "현재 가입된 맴버",
                        buttonName = "전체 보기",
                        showButton = true,
                        listIcon = Icons.Default.DateRange,
                        onClick = { }) {
                        items(currentClubMember.value) { clubmember ->
                            DefaultItem(
                                modifier = Modifier.padding(bottom = 12.dp),
                                radius = 8.dp
                            ) {
                                CompositionLocalProvider(
                                    LocalTextStyle provides TextStyle(color = Color.White)
                                ) {
                                    ClubMemberItem(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(15.dp), clubMember = clubmember
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
private fun ScheduleItem(modifier: Modifier = Modifier, schedule: Schedule) {
    Column(
        modifier
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = schedule.location)
            Image(
                modifier = Modifier.size(smallIcon),
                painter = painterResource(id = R.drawable.pin_button),
                contentDescription = "pin"
            )
        }
        Text(
            text = schedule.date,
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
private fun MatchInfo(modifier: Modifier = Modifier, schedule: Schedule) {
    Row(
        modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Emblem(schedule)
        TeamInfo(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(), schedule.team1
        )
        Text(text = "VS", fontSize = middleFont, fontWeight = FontWeight.Bold)
        TeamInfo(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(), schedule.team2
        )
        Emblem(schedule)
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = ""
        )
    }
}

@Composable
private fun Emblem(schedule: Schedule) {
    Image(
        modifier = Modifier
            .aspectRatio(1f)
            .fillMaxHeight(),
        imageVector = Icons.Default.Settings,
        contentDescription = schedule.team1
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
fun EmptyScheduleContent() {
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
            ) {}
        }
    }
}






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

@Composable
@Preview
fun ScheduleViewPreview() {
    val state = remember {
        mutableStateOf(generateDummyData(5))
    }
    val state1= remember {
        mutableStateOf(generateDummyData1(5))
    }
    ScheduleView_Sample(currentSchedule = state, currentClubMember = state1)
}


@Composable
@Preview
fun EmptyScheduleContentPreview() {
    EmptyScheduleContent()
}

fun generateDummyData(count: Int): List<Schedule> {
    val dummyLocations = listOf("Stadium A", "Stadium B", "Stadium C", "Stadium D", "Stadium E")
    val dummyDates = listOf("2022-01-01", "2022-02-15", "2022-03-10", "2022-04-20", "2022-05-05")
    val dummyTeams = listOf("Alpha", "Beta", "Gamma", "Delta", "Epsilon")

    return (1..count).map {
        Schedule(
            location = dummyLocations.random(),
            date = dummyDates.random(),
            team1 = dummyTeams.random(),
            team2 = dummyTeams.random()
        )
    }
}
fun generateDummyData1(count: Int): List<ClubMember> {
    val dummyName = listOf("홍길동", "가나다", "라마바", "이순신", "ABC")
    val dummyAge = listOf("23", "24", "25", "26", "30")
    val dummyNumer = listOf("1", "2", "3", "4", "5")

    return (1..count).map {
        ClubMember(
            name = dummyName.random(),
            age = dummyAge.random(),
            number = dummyNumer.random(),
        )
    }
}
