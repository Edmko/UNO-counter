package ua.edmko.unocounter.domain.interactor

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ua.edmko.unocounter.domain.entities.Round
import ua.edmko.unocounter.domain.repository.GameRepository

class AddRoundToGame(private val gameRepository: GameRepository): Interactor<AddRoundToGame.Params>() {
    data class Params(val round: Round?)

    override suspend fun doWork(params: Params) {
        withContext(Dispatchers.IO){
            require(params.round !=null)
            gameRepository.addRoundToGame(params.round)
        }
    }
}