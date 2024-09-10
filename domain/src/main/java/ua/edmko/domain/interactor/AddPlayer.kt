package ua.edmko.domain.interactor

import kotlinx.coroutines.withContext
import ua.edmko.domain.CoroutineDispatchers
import ua.edmko.domain.repository.PlayersRepository
import javax.inject.Inject

class AddPlayer @Inject constructor(
    private val playersRepository: PlayersRepository,
    private val dispatchers: CoroutineDispatchers,
) : Interactor<AddPlayer.Params>() {
    override suspend fun doWork(params: Params): Unit = withContext(dispatchers.io) {
        playersRepository.createPlayer(params.name)
    }

    data class Params(val name: String)
}
