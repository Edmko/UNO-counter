package ua.edmko.domain.interactor

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ua.edmko.domain.entities.Round
import ua.edmko.domain.repository.GameRepository
import javax.inject.Inject

class AddRoundToGame @Inject constructor(private val gameRepository: GameRepository) :
    Interactor<AddRoundToGame.Params>() {
    data class Params(val round: Round?)

    override suspend fun doWork(params: Params) {
        withContext(Dispatchers.IO) {
            requireNotNull(params.round) { "Round must not be null" }
            gameRepository.addRoundToGame(params.round)
        }
    }
}