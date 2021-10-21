package ua.edmko.domain.interactor

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ua.edmko.domain.entities.GameSettings
import ua.edmko.domain.repository.GameRepository
import ua.edmko.domain.repository.PlayersRepository
import javax.inject.Inject

class CreateGame @Inject constructor(
    private val gameRepository: GameRepository,
    private val playersRepository: PlayersRepository
) : Interactor<CreateGame.Params>() {

    data class Params(val settings: GameSettings)

    override suspend fun doWork(params: Params) = withContext(Dispatchers.IO) {
        val gameId = params.settings.id
        gameRepository.createGame(params.settings)
        playersRepository.getSelectedPlayers().forEach {
            gameRepository.addPlayerToGame(it.playerId, gameId)
        }
    }
}