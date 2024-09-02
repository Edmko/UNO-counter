package ua.edmko.domain.interactor

import kotlinx.coroutines.flow.Flow
import ua.edmko.domain.entities.Game
import ua.edmko.domain.repository.GameRepository
import javax.inject.Inject

class ObserveGame @Inject constructor(
    private val gameRepository: GameRepository,
) : SubjectInteractor<ObserveGame.Params, Game>() {

    data class Params(
        val gameId: String,
    )

    override fun createObservable(params: Params): Flow<Game> {
        return gameRepository.observeGameById(params.gameId)
    }
}
