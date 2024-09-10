package interactors

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import ua.edmko.domain.entities.Player
import ua.edmko.domain.interactor.ObserveSelectedPlayers
import ua.edmko.domain.repository.PlayersRepository

@RunWith(MockitoJUnitRunner::class)
class ObserveSelectedPlayers {

    private lateinit var observeSelectedPlayers: ObserveSelectedPlayers

    @Mock
    lateinit var playersRepository: PlayersRepository

    @Before
    fun setup() {
        observeSelectedPlayers = ObserveSelectedPlayers(playersRepository)
    }

    @Test
    fun `check all players are selected`() = runTest {
        `when`(playersRepository.observePlayers()).thenReturn(flow { emit(Player.STUB) })
        val players = observeSelectedPlayers.createObservable(Unit).toList().flatten()
        assert(players.all { it.isSelected })
    }
}
