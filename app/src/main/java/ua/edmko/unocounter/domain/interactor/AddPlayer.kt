package ua.edmko.unocounter.domain.interactor

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ua.edmko.unocounter.domain.entities.Player
import ua.edmko.unocounter.domain.repository.PlayersRepository
import javax.inject.Inject

class AddPlayer @Inject constructor(private val playersRepository: PlayersRepository): Interactor<AddPlayer.Params>() {
    override suspend fun doWork(params: Params): Unit = withContext(Dispatchers.IO) {
        playersRepository.createPlayer(params.player)
    }
    data class Params(val player :Player)
}