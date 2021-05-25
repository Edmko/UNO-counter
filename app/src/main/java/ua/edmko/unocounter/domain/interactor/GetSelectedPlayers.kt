package ua.edmko.unocounter.domain.interactor

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ua.edmko.unocounter.domain.entities.Player
import ua.edmko.unocounter.domain.repository.PlayersRepository
import javax.inject.Inject

class GetSelectedPlayers @Inject constructor(private val playersRepository: PlayersRepository): ResultInteractor<Unit, List<Player>>() {
    override suspend fun doWork(params: Unit): List<Player> = withContext(Dispatchers.IO) {
        playersRepository.getSelectedPlayers()
    }
}