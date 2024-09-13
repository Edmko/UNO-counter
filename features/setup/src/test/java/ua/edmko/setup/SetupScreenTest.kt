package ua.edmko.setup

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.edmko.setup.EDIT_GOAL_DIALOG_TAG
import com.edmko.setup.GOAL_FIELD_TAG
import com.edmko.setup.PLAYERS_LIST_TAG
import com.edmko.setup.SELECT_GAME_TYPE_DIALOG_TAG
import com.edmko.setup.SetupContent
import com.edmko.setup.SetupViewState
import com.edmko.setup.TYPE_FIELD_TAG
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import ua.edmko.domain.entities.GameType
import ua.edmko.domain.entities.Player
import ua.edmko.domain.entities.ResultState

@RunWith(RobolectricTestRunner::class)
internal class SetupScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `when type dialog state show option dialog`() {
        SetupViewState(dialog = SetupViewState.DialogType.GameType).asScreen()
        composeTestRule.onNodeWithTag(SELECT_GAME_TYPE_DIALOG_TAG).assertIsDisplayed()
    }

    @Test
    fun `when edit dialog state show edit dialog`() {
        SetupViewState(dialog = SetupViewState.DialogType.EditGoal).asScreen()
        composeTestRule.onNodeWithTag(EDIT_GOAL_DIALOG_TAG).assertIsDisplayed()
    }

    @Test
    fun `when state has players display list`() {
        SetupViewState(players = ResultState.Success(Player.STUB)).asScreen()
        composeTestRule.onNodeWithTag(PLAYERS_LIST_TAG).assertIsDisplayed()
    }

    @Test
    fun `when state has not players not display list`() {
        SetupViewState(players = ResultState.Success(emptyList())).asScreen()
        composeTestRule.onNodeWithTag(PLAYERS_LIST_TAG).assertIsNotDisplayed()
    }

    @Test
    fun `display proper goal`() {
        val goal = 12345
        SetupViewState(goal = goal).asScreen()
        composeTestRule
            .onNodeWithTag(testTag = GOAL_FIELD_TAG, useUnmergedTree = true)
            .assertTextContains(
                value = goal.toString(),
                substring = true,
                ignoreCase = true,
            )
    }

    @Test
    fun `display proper type`() {
        val type = GameType.COLLECTIVE
        SetupViewState(gameType = type).asScreen()
        composeTestRule
            .onNodeWithTag(testTag = TYPE_FIELD_TAG, useUnmergedTree = true)
            .assertTextEquals(type.name)
    }

    private fun SetupViewState.asScreen() = composeTestRule.setContent {
        SetupContent(
            state = this,
            event = {},
        )
    }
}
