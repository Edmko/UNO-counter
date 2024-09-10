package ua.edmko.setup

import com.edmko.setup.ChangeGoal
import com.edmko.setup.DismissDialog
import com.edmko.setup.EditPlayers
import com.edmko.setup.OnGoalClickEvent
import com.edmko.setup.OnTypeClickEvent
import com.edmko.setup.SetGameType
import com.edmko.setup.SetupNavigator
import com.edmko.setup.SetupViewModel
import com.edmko.setup.SetupViewState
import com.edmko.setup.StartGame
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import ua.edmko.domain.entities.GameType
import ua.edmko.domain.entities.Player
import ua.edmko.domain.interactor.CreateGame
import ua.edmko.domain.interactor.ObserveSelectedPlayers

@RunWith(MockitoJUnitRunner::class)
internal class SetupViewModelTest {

    @Mock
    lateinit var viewModel: SetupViewModel

    @Mock
    lateinit var createGame: CreateGame

    @Mock
    lateinit var observeSelectedPlayers: ObserveSelectedPlayers

    @Mock
    lateinit var navigator: SetupNavigator

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setup() {
        val players = listOf(
            Player(266542, "John Smith"),
            Player(254153, "Dali Bali"),
        )
        Mockito
            .`when`(observeSelectedPlayers.createObservable(Unit))
            .thenReturn(flow { emit(players) })
        viewModel = SetupViewModel(createGame, observeSelectedPlayers, navigator)
    }

    @Test
    fun `check ChangeGoal event changes state`() {
        val goal = 500
        viewModel.obtainEvent(ChangeGoal(500))
        val state = viewModel.viewStates().value!!
        Assert.assertEquals(goal, state.goal)
    }

    @Test
    fun `check OnGoalClickEvent change state to opened dialog`() {
        viewModel.obtainEvent(OnGoalClickEvent)
        val state = viewModel.viewStates().value!!
        Assert.assertEquals(state.dialog, SetupViewState.DialogType.EditGoal)
    }

    @Test
    fun `check OnTypeClickEvent change state to opened dialog`() {
        viewModel.obtainEvent(OnTypeClickEvent)
        val state = viewModel.viewStates().value!!
        Assert.assertEquals(state.dialog, SetupViewState.DialogType.GameType)
    }

    @Test
    fun `check EditPlayers navigate to players screen`() = runTest {
        viewModel.obtainEvent(EditPlayers)
        Mockito.verify(navigator).toPlayers()
    }

    @Test
    fun `check DismissDialog changes viewState`() {
        viewModel.obtainEvent(DismissDialog)
        val state = viewModel.viewStates().value!!
        Assert.assertEquals(state.dialog, null)
    }

    @Test
    fun `check SetGameType changes viewState`() {
        val gameType = GameType.COLLECTIVE
        viewModel.obtainEvent(SetGameType(gameType))
        val state = viewModel.viewStates().value!!
        Assert.assertEquals(state.gameType, gameType)
    }

    @Test
    fun `check StartGame creates game and navigate to game`() = runTest {
        viewModel.obtainEvent(StartGame)
        Mockito.verify(createGame).invoke(any())
        Mockito.verify(navigator).toGame(anyString())
    }
}
