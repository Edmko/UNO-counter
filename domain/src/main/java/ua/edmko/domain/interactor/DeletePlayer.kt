package ua.edmko.domain.interactor

import kotlinx.coroutines.withContext
import ua.edmko.domain.CoroutineDispatchers
import ua.edmko.domain.repository.PlayersRepository
import javax.inject.Inject

class DeletePlayer @Inject constructor(
    private val playersRepository: PlayersRepository,
    private val dispatchers: CoroutineDispatchers,
) : Interactor<DeletePlayer.Params>() {

    override suspend fun doWork(params: Params) = withContext(dispatchers.io) {
        playersRepository.removePlayer(params.id)
    }

    data class Params(val id: Long)
}
