package ua.edmko.domain.interactor

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ua.edmko.domain.repository.PlayersRepository
import javax.inject.Inject

class DeletePlayer @Inject constructor(private val playersRepository: PlayersRepository): Interactor<DeletePlayer.Params>() {
    override suspend fun doWork(params: Params)  = withContext(Dispatchers.IO) {
        playersRepository.removePlayer(params.id)
    }

    data class Params(val id: Long)
}