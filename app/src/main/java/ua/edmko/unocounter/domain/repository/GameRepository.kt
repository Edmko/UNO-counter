package ua.edmko.unocounter.domain.repository

import kotlinx.coroutines.flow.Flow
import ua.edmko.unocounter.domain.entities.Game
import ua.edmko.unocounter.domain.entities.GameSettings
import ua.edmko.unocounter.domain.entities.Round

interface GameRepository {
    suspend fun createGame(gameSettings: GameSettings)
    suspend fun addPlayerToGame(playerId: Long, gameId: String)
    fun observeGameById(gameId: String): Flow<Game>
    suspend fun addRoundToGame(round: Round)
    fun getGameList(): Flow<List<Game>>
}