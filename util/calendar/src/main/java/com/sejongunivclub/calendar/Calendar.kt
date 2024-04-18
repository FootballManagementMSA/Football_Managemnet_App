package com.sejongunivclub.calendar

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sejongunivclub.calendar.model.Date
import com.sejongunivclub.calendar.viewmodel.CalendarViewModel
import com.sejongunivclub.ui_component.buttons.DefaultRoundedButton
import com.sejongunivclub.ui_component.VerticalSpacer
import com.sejongunivclub.ui_component.values.bigFont
import com.sejongunivclub.ui_component.values.darkButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun Calendar(
    modifier: Modifier = Modifier,
    scope: CoroutineScope = rememberCoroutineScope(),
    viewModel: CalendarViewModel = hiltViewModel(),
    pageCount: Int = 12,
    onSelect: (Date) -> Unit
) {
    val context = LocalContext.current
    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadCalendar(pageCount)
    }
    when (uiState.value) {
        CalendarState.Loading -> {
            Box(modifier.fillMaxSize()) {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }
        }

        is CalendarState.Success -> {
            CalendarContent(
                uiState,
                modifier,
                scope,
                pageCount,
                viewModel,
                context,
                onSelect
            )
        }
    }
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun CalendarContent(
    uiState: State<CalendarState>,
    modifier: Modifier,
    scope: CoroutineScope,
    pageCount: Int,
    viewModel: CalendarViewModel,
    context: Context,
    onSelect: (Date) -> Unit
) {
    val calendarPages = remember { (uiState.value as CalendarState.Success).calendarPage }
    val pagerState = rememberPagerState { pageCount }
    Column(
        modifier
            .testTag("Calendar")
            .background(Color.White)
    ) {
        Text(text = "날짜 선택", fontSize = bigFont)
        VerticalSpacer(value = 10)
        CalendarControlView(
            Modifier,
            calendarPages[pagerState.currentPage].year,
            calendarPages[pagerState.currentPage].month,
            onPrev = {
                scope.launch {
                    pagerState.animateScrollToPage(
                        (pagerState.currentPage - 1).coerceAtLeast(
                            0
                        )
                    )
                }
            },
            onNext = {
                scope.launch {
                    pagerState.animateScrollToPage(
                        (pagerState.currentPage + 1).coerceAtMost(
                            pageCount
                        )
                    )
                }
            }
        )
        VerticalSpacer(value = 10)
        HorizontalPager(
            modifier = modifier.testTag("pager").weight(1f), state = pagerState
        ) { monthIndex ->
            Month(
                calendarPages[monthIndex].calendar,
                viewModel.selectedIndex,
                monthIndex
            )
        }
        DefaultRoundedButton(
            modifier = Modifier.testTag("select"),
            cornerRadius = 32.dp,
            buttonText = "선택",
            buttonColor = darkButton
        ) {
            if (viewModel.selectedIndex.value == Triple(-1, -1, -1)) {
                Toast.makeText(context, "날짜를 선택 해 주세요", Toast.LENGTH_SHORT).show()
            } else {
                val (month, week, day) = viewModel.selectedIndex.value
                onSelect(calendarPages[month].calendar[day][week]!!)
            }
        }
    }
}

@Preview
@Composable
fun CalendarContentPreview() {
    Calendar(modifier = Modifier.height(400.dp)) {

    }
}