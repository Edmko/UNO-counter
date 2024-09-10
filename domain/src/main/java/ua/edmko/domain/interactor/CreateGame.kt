package ua.edmko.domain.interactor

import kotlinx.coroutines.withContext
import ua.edmko.domain.CoroutineDispatchers
import ua.edmko.domain.entities.GameSettings
import ua.edmko.domain.repository.GameRepository
import ua.edmko.domain.repository.PlayersRepository
import javax.inject.Inject

class CreateGame @Inject constructor(
    private val gameRepository: GameRepository,
    private val playersRepository: PlayersRepository,
    private val dispatchers: CoroutineDispatchers,
) : Interactor<CreateGame.Params>() {

    data class Params(val settings: GameSettings)

    override suspend fun doWork(params: Params) = withContext(dispatchers.io) {
        val gameId = params.settings.id
        val selectedPlayers = playersRepository.getSelectedPlayers()
        if (selectedPlayers.isEmpty()) throw IllegalStateException("Cannot create game without players")
        gameRepository.createGame(params.settings)
        gameRepository.addPlayersToGame(selectedPlayers, gameId)
    }
}
