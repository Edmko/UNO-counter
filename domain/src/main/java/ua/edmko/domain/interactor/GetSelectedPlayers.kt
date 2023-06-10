package ua.edmko.domain.interactor

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ua.edmko.domain.entities.Player
import ua.edmko.domain.repository.PlayersRepository
import javax.inject.Inject

class GetSelectedPlayers @Inject constructor(private val playersRepository: PlayersRepository) : ResultInteractor<Unit, List<Player>>() {
    override suspend fun doWork(params: Unit): List<Player> = withContext(Dispatchers.IO) {
        playersRepository.getSelectedPlayers()
    }
}
