package interactors

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import ua.edmko.domain.CoroutineDispatchers
import ua.edmko.domain.entities.GameSettings
import ua.edmko.domain.entities.Player
import ua.edmko.domain.interactor.CreateGame
import ua.edmko.domain.repository.GameRepository
import ua.edmko.domain.repository.PlayersRepository

@RunWith(MockitoJUnitRunner::class)
class CreateGameTest {

    private lateinit var createGame: CreateGame

    @Mock
    lateinit var gameRepository: GameRepository

    @Mock
    lateinit var playersRepository: PlayersRepository

    private val dispatchers: CoroutineDispatchers =
        CoroutineDispatchers(Dispatchers.Unconfined, Dispatchers.Unconfined, Dispatchers.Unconfined)

    @Before
    fun setup() {
        createGame = CreateGame(gameRepository, playersRepository, dispatchers)
    }

    @Test
    fun `check game created and players added`() = runTest {
        val players = Player.STUB
        val settings = GameSettings.EMPTY
        `when`(playersRepository.getSelectedPlayers()).thenReturn(players)
        createGame.executeSync(CreateGame.Params(settings))
        verify(gameRepository).createGame(settings)
        verify(gameRepository).addPlayersToGame(players, settings.id)
    }
}
