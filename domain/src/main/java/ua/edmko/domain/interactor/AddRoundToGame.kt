package ua.edmko.domain.interactor

import kotlinx.coroutines.withContext
import ua.edmko.domain.CoroutineDispatchers
import ua.edmko.domain.entities.Round
import ua.edmko.domain.repository.GameRepository
import javax.inject.Inject

class AddRoundToGame @Inject constructor(
    private val gameRepository: GameRepository,
    private val dispatchers: CoroutineDispatchers,
) : Interactor<AddRoundToGame.Params>() {

    data class Params(
        val round: Round,
    )

    override suspend fun doWork(params: Params) = withContext(dispatchers.io) {
        gameRepository.addRoundToGame(params.round)
    }
}
