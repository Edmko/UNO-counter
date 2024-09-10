package ua.edmko.domain.interactor

import kotlinx.coroutines.withContext
import ua.edmko.domain.CoroutineDispatchers
import ua.edmko.domain.entities.Player
import ua.edmko.domain.repository.PlayersRepository
import javax.inject.Inject

class UpdatePlayer @Inject constructor(
    private val playersRepository: PlayersRepository,
    private val dispatchers: CoroutineDispatchers,
) : Interactor<UpdatePlayer.Params>() {

    override suspend fun doWork(params: Params) = withContext(dispatchers.io) {
        playersRepository.updatePlayer(params.player)
    }

    data class Params(val player: Player)
}
