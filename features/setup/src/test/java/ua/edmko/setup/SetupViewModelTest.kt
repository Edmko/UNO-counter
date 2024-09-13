package ua.edmko.setup

import com.edmko.setup.ChangeGoal
import com.edmko.setup.DismissDialog
import com.edmko.setup.EditPlayers
import com.edmko.setup.GameSettingEvent
import com.edmko.setup.OnGoalClickEvent
import com.edmko.setup.OnTypeClickEvent
import com.edmko.setup.SetGameType
import com.edmko.setup.SetupNavigator
import com.edmko.setup.SetupViewModel
import com.edmko.setup.SetupViewState
import com.edmko.setup.StartGame
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.times
import ua.edmko.domain.entities.GameType
import ua.edmko.domain.entities.ResultState
import ua.edmko.domain.interactor.CreateGame
import ua.edmko.domain.interactor.ObserveSelectedPlayers
import ua.edmko.testing.BaseViewModelTest
import ua.edmko.testing.extensions.returnError
import ua.edmko.testing.extensions.returnSuccess
import ua.edmko.testing.extensions.returnValue
import ua.edmko.testing.extensions.verifyCall
import ua.edmko.testing.selectedPlayersList

@RunWith(MockitoJUnitRunner::class)
internal class SetupViewModelTest : BaseViewModelTest<SetupViewState, GameSettingEvent, SetupViewModel>() {

    @Mock
    lateinit var createGame: CreateGame

    @Mock
    lateinit var observeSelectedPlayers: ObserveSelectedPlayers

    @Mock
    lateinit var navigator: SetupNavigator

    override fun createViewModel(): SetupViewModel {
        observeSelectedPlayers.returnValue(ResultState.Success(selectedPlayersList))
        return SetupViewModel(createGame, observeSelectedPlayers, navigator)
    }

    @Test
    fun `check ChangeGoal event changes state`() = runTest {
        val goal = 500
        test(
            events = listOf(ChangeGoal(goal)),
            assertions = listOf(
                createAssertion(goal) { it.goal },
            ),
        )
    }

    @Test
    fun `check OnGoalClickEvent change state to opened dialog`() = runTest {
        test(
            events = listOf(OnGoalClickEvent),
            assertions = listOf(
                createAssertion(SetupViewState.DialogType.EditGoal) { it.dialog },
            ),
        )
    }

    @Test
    fun `check OnTypeClickEvent change state to opened dialog`() = runTest {
        test(
            events = listOf(OnTypeClickEvent),
            assertions = listOf(
                createAssertion(SetupViewState.DialogType.GameType) { it.dialog },
            ),
        )
    }

    @Test
    fun `check EditPlayers navigate to players screen`() = runTest {
        test(
            events = listOf(EditPlayers),
            verifiers = listOf { Mockito.verify(navigator).toPlayers() },
        )
    }

    @Test
    fun `check DismissDialog changes viewState`() = runTest {
        test(
            events = listOf(DismissDialog),
            assertions = listOf(
                createAssertion(null) { it.dialog },
            ),
        )
    }

    @Test
    fun `check SetGameType changes viewState`() = runTest {
        val gameType = GameType.COLLECTIVE
        test(
            events = listOf(SetGameType(gameType)),
            assertions = listOf(
                createAssertion(gameType) { it.gameType },
            ),
        )
    }

    @Test
    fun `check StartGame creates game and navigate to game`() = runTest {
        createGame.returnSuccess()
        test(
            events = listOf(StartGame),
            verifiers = listOf(
                { createGame.verifyCall() },
                { Mockito.verify(navigator).toGame(anyString()) },
            ),
        )
    }

    @Test
    fun `check StartGame fail if cant create game`() = runTest {
        createGame.returnError(Exception())
        test(
            events = listOf(StartGame),
            verifiers = listOf { Mockito.verify(navigator, times(0)).toGame(anyString()) },
        )
    }
}
