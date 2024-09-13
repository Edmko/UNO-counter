package ua.edmko.setup

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import ua.edmko.core.ui.components.PLAYER_ITEM_TEST_TAG
import ua.edmko.game.EDIT_SCORE_DIALOG_TAG
import ua.edmko.game.EditPlayerState
import ua.edmko.game.GameScreen
import ua.edmko.game.GameViewState
import ua.edmko.testing.firstPlayerFromGame
import ua.edmko.testing.game

@RunWith(RobolectricTestRunner::class)
internal class GameScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `when edit player state not null show dialog`() {
        GameViewState(editPlayerState = EditPlayerState(firstPlayerFromGame)).asScreen()
        composeTestRule
            .onNodeWithTag(EDIT_SCORE_DIALOG_TAG)
            .assertIsDisplayed()
    }

    @Test
    fun `when edit player state is null do not show dialog`() {
        GameViewState(editPlayerState = null).asScreen()
        composeTestRule
            .onNodeWithTag(EDIT_SCORE_DIALOG_TAG)
            .assertIsNotDisplayed()
    }

    @Test
    fun `check player item count is equal to state`() {
        GameViewState(game = game).asScreen()
        val playerItemNodesCount = composeTestRule
            .onAllNodes(hasTestTag(PLAYER_ITEM_TEST_TAG))
            .fetchSemanticsNodes()
            .size
        assert(playerItemNodesCount == game.players.size)
    }

    private fun GameViewState.asScreen() = composeTestRule.setContent {
        GameScreen(
            state = this,
            event = {},
        )
    }
}
