package ua.edmko.players

import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import ua.edmko.domain.interactor.AddPlayer
import ua.edmko.domain.interactor.DeletePlayer
import ua.edmko.domain.interactor.ObservePlayers
import ua.edmko.domain.interactor.UpdatePlayer
import ua.edmko.testing.BaseViewModelTest
import ua.edmko.testing.extensions.returnSuccess
import ua.edmko.testing.extensions.returnValue
import ua.edmko.testing.extensions.verifyCall
import ua.edmko.testing.firstPlayerFromGame
import ua.edmko.testing.selectedPlayersList

@RunWith(MockitoJUnitRunner::class)
internal class PlayersViewModelTest : BaseViewModelTest<PlayersViewState, PlayersEvent, PlayersViewModel>() {

    @Mock
    lateinit var observerPlayers: ObservePlayers

    @Mock
    lateinit var addPlayer: AddPlayer

    @Mock
    lateinit var updatePlayer: UpdatePlayer

    @Mock
    lateinit var deletePlayer: DeletePlayer

    @Mock
    lateinit var navigator: PlayersNavigator

    override fun createViewModel(): PlayersViewModel {
        observerPlayers.returnValue(selectedPlayersList)
        return PlayersViewModel(observerPlayers, addPlayer, updatePlayer, deletePlayer, navigator)
    }

    @Test
    fun `when AddPlayerButton event show dialog`() = runTest {
        test(
            events = listOf(AddPlayerButton),
            assertions = listOf(
                createAssertion(AddPlayer) { it.dialog },
            ),
        )
    }

    @Test
    fun `when CreatePlayer event call add player and disable dialog`() = runTest {
        addPlayer.returnSuccess()
        val name = "player name"
        test(
            events = listOf(CreatePlayer(name)),
            verifiers = listOf { addPlayer.verifyCall(AddPlayer.Params(name)) },
            assertions = listOf(
                createAssertion(null) { it.dialog },
            ),
        )
    }

    @Test
    fun `when UpdatePlayersSelection event run update selection`() = runTest {
        updatePlayer.returnSuccess()
        val player = firstPlayerFromGame
        test(
            events = listOf(UpdatePlayersSelection(player)),
            verifiers = listOf { updatePlayer.verifyCall(UpdatePlayer.Params(player)) },
        )
    }

    @Test
    fun `when OnDeletePlayer event run deletion`() = runTest {
        deletePlayer.returnSuccess()
        val player = firstPlayerFromGame
        test(
            events = listOf(DeletePlayerEvent(player)),
            verifiers = listOf { deletePlayer.verifyCall(DeletePlayer.Params(player.playerId)) },
        )
    }

    @Test
    fun `when NavigateBack call navigation`() = runTest {
        test(
            events = listOf(NavigateBack),
            verifiers = listOf { verify(navigator).back() },
        )
    }

    @Test
    fun `when DismissDialog hide dialog`() = runTest {
        test(
            events = listOf(DismissDialog),
            assertions = listOf(
                createAssertion(null) { it.dialog },
            ),
        )
    }

    @Test
    fun `when EditPlayer show edit dialog`() = runTest {
        test(
            events = listOf(EditPlayer(firstPlayerFromGame)),
            assertions = listOf(
                createAssertion(EditPlayersName(firstPlayerFromGame)) { it.dialog },
            ),
        )
    }

    @Test
    fun `when ChangePlayersName run update`() = runTest {
        updatePlayer.returnSuccess()
        val newName = "newName"
        val newPlayer = firstPlayerFromGame.copy(name = newName)
        test(
            events = listOf(ChangePlayersName(firstPlayerFromGame, newName)),
            verifiers = listOf { updatePlayer.verifyCall(UpdatePlayer.Params(newPlayer)) },
        )
    }
}
