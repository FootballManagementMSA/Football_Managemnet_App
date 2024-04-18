package com.sejongunivclub.auto_complete

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.printToLog
import androidx.compose.ui.unit.sp
import com.sejongunivclub.ui_component.HorizontalSpacer
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AutoCompleteTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val categoryList = listOf(
        TempClubInfo("Liverpool", "2221"),
        TempClubInfo("Arsenal", "2223"),
        TempClubInfo("Man City", "33130"),
        TempClubInfo("Manchest United", "4678"),
        TempClubInfo("Brighton", "7890"),
        TempClubInfo("brentford", "36458"),
        TempClubInfo("Burnley", "w234"),
        TempClubInfo("luton", "wy765"),
        TempClubInfo("Siuuu", "133252")
    )

    private val viewModel = AutoCompleteViewModel()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            AutoCompleteTestScreen()
        }
        composeTestRule.onRoot().printToLog("TAG")
        Thread.sleep(500)
    }

    @Composable
    private fun AutoCompleteTestScreen() {
        AutoCompleteSearchBox(
            placeholder = "상대 구단을 검색해주세요.",
            items = categoryList,
            itemFilter = {
                it.name
            },
            viewModel = viewModel
        ) {
            Text(text = it.name)
            HorizontalSpacer(value = 5)
            Text(fontSize = 10.sp, text = "구단 고유 코드 : ${it.code}")
        }
    }

    @Test
    fun auto_complete_should_show() {
        composeTestRule.onNodeWithTag("AutoComplete").assertIsDisplayed()
    }

    @Test
    fun auto_complete_text_field_should_show() {
        composeTestRule.onNodeWithTag("AutoCompleteTextField").assertIsDisplayed()
    }

    @Test
    fun when_text_field_input_show_search_item() {
        composeTestRule.onNodeWithTag("AutoCompleteTextField").performClick()
        composeTestRule.onNodeWithTag("AutoCompleteTextField").printToLog("AutoCompleteTextField")
        composeTestRule.onNodeWithTag("AutoCompleteTextField").performTextInput("b")
        composeTestRule
            .onNodeWithTag("SearchDropdown")
            .onChildren()
            .assertCountEquals(3)
        Thread.sleep(5000)
    }

    @Test
    fun when_selecting_value_from_dropdown_textfield_should_update() {
        composeTestRule.onNodeWithTag("AutoCompleteTextField").performClick()
        composeTestRule.onNodeWithTag("AutoCompleteTextField")
            .performTextInput("siu")
        composeTestRule.onNodeWithText("Siuuu")
            .assertExists()
            .performClick()
        composeTestRule.onNodeWithTag("AutoCompleteTextField")
            .assert(hasText("Siuuu"))
    }

}