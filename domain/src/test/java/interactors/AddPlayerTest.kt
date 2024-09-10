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
import ua.edmko.domain.interactor.AddPlayer
import ua.edmko.domain.repository.PlayersRepository

@RunWith(MockitoJUnitRunner::class)
class AddPlayerTest {

    private lateinit var addPlayer: AddPlayer

    @Mock
    lateinit var playersRepository: PlayersRepository

    private val dispatchers: CoroutineDispatchers =
        CoroutineDispatchers(Dispatchers.Unconfined, Dispatchers.Unconfined, Dispatchers.Unconfined)

    @Before
    fun setup() {
        addPlayer = AddPlayer(playersRepository, dispatchers)
    }

    @Test
    fun `check player added`() = runTest {
        val name = "User name"
        addPlayer.executeSync(AddPlayer.Params(name))
        verify(playersRepository).createPlayer(name)
    }
}
