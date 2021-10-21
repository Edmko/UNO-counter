package ua.edmko.domain.interactor

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ua.edmko.domain.entities.Player
import ua.edmko.domain.repository.PlayersRepository
import javax.inject.Inject

class AddPlayer @Inject constructor(private val playersRepository: PlayersRepository): Interactor<AddPlayer.Params>() {
    override suspend fun doWork(params: Params): Unit = withContext(Dispatchers.IO) {
        playersRepository.createPlayer(params.name)
    }
    data class Params(val name: String)
}