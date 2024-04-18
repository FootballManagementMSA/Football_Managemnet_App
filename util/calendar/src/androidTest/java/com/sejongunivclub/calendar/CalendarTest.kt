package com.sejongunivclub.calendar

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.compose.ui.test.printToLog
import com.sejongunivclub.calendar.model.Date
import com.sejongunivclub.calendar.util.CalendarUtil.currentDay
import com.sejongunivclub.calendar.util.CalendarUtil.currentDayOfWeek
import com.sejongunivclub.calendar.util.CalendarUtil.currentMonth
import com.sejongunivclub.calendar.util.CalendarUtil.currentYear
import com.sejongunivclub.calendar.values.dayOfWeekList
import com.sejongunivclub.calendar.viewmodel.CalendarViewModel
import kotlinx.coroutines.Dispatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class CalendarTest {

    @get:Rule
    val composeRule = createComposeRule()

    private val viewModel: CalendarViewModel = CalendarViewModel(Dispatchers.IO)

    private val currentDate = Date(
        currentYear,
        currentMonth,
        currentDay,
        dayOfWeekList[currentDayOfWeek - 1]
    )

    @Before
    fun setUp() {
        composeRule.setContent {
            TestScreen()
        }
        composeRule.onRoot().printToLog("TAG")
        Thread.sleep(500)
    }

    @Composable
    private fun TestScreen() {
        val testState = remember { mutableStateOf(Date(0, 0, 0, "")) }
        Text(modifier = Modifier.testTag("result"), text = testState.value.toString())
        Calendar(viewModel = viewModel) {
            testState.value = it
        }
    }

    @Test
    fun `calendar_should_shows`() {
        composeRule.onNodeWithTag("Calendar").assertIsDisplayed()
    }

    @Test
    fun `when_day_clicks_selected_index_in_viewModel_should_not_equals_to_notSelected`() {
        composeRule.onNodeWithTag("$currentDate", true).assertIsDisplayed()
        composeRule.onNodeWithTag("$currentDate", true).performClick()
        println("selectedIndex : ${viewModel.selectedIndex.value}")
        assert(Triple(-1, -1, -1) != viewModel.selectedIndex.value)
    }

    @Test
    fun `current_date_should_be_exact`() {
        composeRule.onNodeWithTag("date").assert(hasText("$currentYear 년 $currentMonth 월"))
    }

    @Test
    fun `when_next_button_clicks_go_to_next_month`() {
        composeRule.onNodeWithTag("date").assert(hasText("$currentYear 년 $currentMonth 월"))
        composeRule.onNodeWithTag("next", true).performClick()
        if (currentMonth == 12) {
            composeRule.onNodeWithTag("date").assert(hasText("$currentYear 년 $currentMonth 월"))
        } else {
            composeRule.onNodeWithTag("date").assert(hasText("$currentYear 년 ${currentMonth + 1} 월"))
        }
    }

    @Test
    fun `when_prev_button_clicks_go_to_previous_month`() {
        composeRule.onNodeWithTag("date").assert(hasText("$currentYear 년 $currentMonth 월"))
        composeRule.onNodeWithTag("prev", true).performClick()
        if (currentMonth == 1) {
            composeRule.onNodeWithTag("date").assert(hasText("$currentYear 년 $currentMonth 월"))
        } else {
            composeRule.onNodeWithTag("date").assert(hasText("$currentYear 년 ${currentMonth - 1}"))
        }
    }

    @Test
    fun `Click_on_the_day_and_then_click_the_select_button_to_display_the_results`() {
        composeRule.onNodeWithTag("$currentDate", true).performClick()
        composeRule.onNodeWithTag("select").performClick()
        composeRule.onNodeWithTag("result").assert(hasText("$currentDate"))
    }

    @Test
    fun `If_you_click_the_Select_button_without_clicking_on_a_day_the_results_are_not_displayed`() {
        composeRule.onNodeWithTag("select").performClick()
        composeRule.onNodeWithTag("result").assert(hasText("${Date(0, 0, 0, "")}"))
    }

    @Test
    fun `If_you_slide_the_pager_from_right_to_left_you_move_on_to_the_next_month`() {
        val page = currentMonth - 1
        composeRule.onNodeWithTag("date").assert(hasText("$currentYear 년 $currentMonth 월"))
        composeRule.onNodeWithTag("pager").performScrollToIndex(page + 1)
        if (currentMonth == 12) {
            composeRule.onNodeWithTag("date").assert(hasText("$currentYear 년 $currentMonth 월"))
        } else {
            composeRule.onNodeWithTag("date").assert(hasText("$currentYear 년 ${currentMonth + 1} 월"))
        }
    }

    @Test
    fun `If_you_slide_the_pager_from_left_to_right_you_move_on_to_the_previous_month`() {
        val page = currentMonth - 1
        composeRule.onNodeWithTag("date").assert(hasText("$currentYear 년 $currentMonth 월"))
        composeRule.onNodeWithTag("pager").performScrollToIndex((page - 1).coerceAtLeast(0))
        if (currentMonth == 1) {
            composeRule.onNodeWithTag("date").assert(hasText("$currentYear 년 $currentMonth 월"))
        } else {
            composeRule.onNodeWithTag("date").assert(hasText("$currentYear 년 ${currentMonth - 1}"))
        }
    }
}