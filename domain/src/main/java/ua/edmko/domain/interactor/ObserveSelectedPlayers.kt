package ua.edmko.domain.interactor

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ua.edmko.domain.entities.Player
import ua.edmko.domain.entities.ResultState
import ua.edmko.domain.entities.asResult
import ua.edmko.domain.repository.PlayersRepository
import javax.inject.Inject

class ObserveSelectedPlayers @Inject constructor(
    private val playersRepository: PlayersRepository,
) : SubjectInteractor<Unit, ResultState<List<Player>>>() {

    override fun createObservable(params: Unit): Flow<ResultState<List<Player>>> {
        return playersRepository
            .observePlayers()
            .map { it.filter { player -> player.isSelected } }
            .asResult()
    }
}
