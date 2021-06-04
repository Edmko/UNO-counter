package ua.edmko.unocounter.domain.interactor

import kotlinx.coroutines.flow.Flow
import ua.edmko.unocounter.domain.entities.Game
import ua.edmko.unocounter.domain.repository.GameRepository

class ObserveGame(private val gameRepository: GameRepository): SubjectInteractor<ObserveGame.Params, Game>() {
    data class Params(val gameId: String)

    override fun createObservable(params: Params): Flow<Game> {
        return gameRepository.observeGameById(params.gameId)
    }
}