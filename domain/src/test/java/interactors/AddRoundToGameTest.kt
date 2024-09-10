package interactors

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import ua.edmko.domain.CoroutineDispatchers
import ua.edmko.domain.entities.Round
import ua.edmko.domain.interactor.AddRoundToGame
import ua.edmko.domain.repository.GameRepository

@RunWith(MockitoJUnitRunner::class)
class AddRoundToGameTest {

    private lateinit var addRoundToGame: AddRoundToGame

    @Mock
    lateinit var gameRepository: GameRepository

    private val dispatchers: CoroutineDispatchers =
        CoroutineDispatchers(Dispatchers.Unconfined, Dispatchers.Unconfined, Dispatchers.Unconfined)

    @Before
    fun setup() {
        addRoundToGame = AddRoundToGame(gameRepository, dispatchers)
    }

    @Test
    fun `check round added`() = runTest {
        val round = Round.EMPTY
        addRoundToGame.executeSync(AddRoundToGame.Params(round))
        verify(gameRepository).addRoundToGame(round)
    }
}
