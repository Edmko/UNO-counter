package ua.edmko.setup

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.testing.invoke
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.robolectric.RobolectricTestRunner
import ua.edmko.domain.interactor.AddRoundToGame
import ua.edmko.domain.interactor.ObserveGame
import ua.edmko.game.ConfirmEdition
import ua.edmko.game.DismissDialog
import ua.edmko.game.EditPlayerState
import ua.edmko.game.EditScore
import ua.edmko.game.GameEvent
import ua.edmko.game.GameNavigator
import ua.edmko.game.GameScreenRoute
import ua.edmko.game.GameViewModel
import ua.edmko.game.GameViewState
import ua.edmko.game.NavigateBack
import ua.edmko.game.NextRound
import ua.edmko.game.SetWinner
import ua.edmko.testing.BaseViewModelTest
import ua.edmko.testing.extensions.returnSuccess
import ua.edmko.testing.extensions.returnValue
import ua.edmko.testing.extensions.verifyCall
import ua.edmko.testing.firstPlayerFromGame
import ua.edmko.testing.game
import ua.edmko.testing.gameSettings

@RunWith(RobolectricTestRunner::class)
internal class GameViewModelTest : BaseViewModelTest<GameViewState, GameEvent, GameViewModel>() {

    @get:Rule
    var rule: MockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var observeGame: ObserveGame

    @Mock
    lateinit var addRoundToGame: AddRoundToGame

    @Mock
    lateinit var navigator: GameNavigator

    override fun createViewModel(): GameViewModel {
        MockitoAnnotations.openMocks(this)
        val route = GameScreenRoute(gameSettings.id)
        val state = SavedStateHandle(route = route)
        observeGame.returnValue(game)
        return GameViewModel(state, observeGame, addRoundToGame, navigator)
    }

    @After
    fun tearDownMockito() {
        Mockito.validateMockitoUsage()
    }

    @Test
    fun `check EditScore event set editPlayerState`() = runTest {
        test(
            events = listOf(EditScore(firstPlayerFromGame)),
            assertions = listOf(
                createAssertion(EditPlayerState(firstPlayerFromGame)) { it.editPlayerState },
            ),
        )
    }

    @Test
    fun `check ConfirmEdition set score for new value and disable edit player state`() = runTest {
        val score = "100"
        test(
            events = listOf(EditScore(firstPlayerFromGame), ConfirmEdition(score)),
            assertions = listOf(
                createAssertion(score.toInt()) { it.currentRound.result[firstPlayerFromGame.playerId] },
                createAssertion(null) { it.editPlayerState },
            ),
        )
    }

    @Test
    fun `check DismissDialog disable dialog`() = runTest {
        val player = game.players.first()
        test(
            events = listOf(EditScore(player), DismissDialog),
            assertions = listOf(
                createAssertion(null) { it.editPlayerState },
            ),
        )
    }

    @Test
    fun `check NavigateBack use navigation`() = runTest {
        test(
            events = listOf(NavigateBack),
            verifiers = listOf { Mockito.verify(navigator).back() },
        )
    }

    @Test
    fun `check NextRound set new round with increased roundNum`() = runTest {
        addRoundToGame.returnSuccess()
        val stateBefore = viewState
        test(
            events = listOf(NextRound),
            verifiers = listOf { addRoundToGame.verifyCall() },
            assertions = listOf(
                createAssertion(stateBefore.currentRound.roundNum + 1) { it.currentRound.roundNum },
            ),
        )
    }

    @Test
    fun `check SetWinner setup new winner in state`() = runTest {
        test(
            events = listOf(SetWinner(firstPlayerFromGame)),
            assertions = listOf(
                createAssertion(firstPlayerFromGame.playerId) { it.currentRound.winner },
            ),
        )
    }
}
