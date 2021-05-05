package ua.edmko.unocounter.domain.interactor

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ua.edmko.unocounter.domain.entities.Player
import ua.edmko.unocounter.domain.repository.PlayersRepository
import javax.inject.Inject

class UpdatePlayer @Inject constructor(private val playersRepository: PlayersRepository): Interactor<UpdatePlayer.Params>() {
    override suspend fun doWork(params: Params)  = withContext(Dispatchers.IO) {
        playersRepository.updatePlayer(params.player)
    }

    data class Params(val player: Player)
}