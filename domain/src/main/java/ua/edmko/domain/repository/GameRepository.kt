package ua.edmko.domain.repository

import kotlinx.coroutines.flow.Flow
import ua.edmko.domain.entities.Game
import ua.edmko.domain.entities.GameSettings
import ua.edmko.domain.entities.Round

interface GameRepository {
    suspend fun createGame(gameSettings: GameSettings)
    suspend fun addPlayerToGame(playerId: Long, gameId: String)
    fun observeGameById(gameId: String): Flow<Game>
    suspend fun addRoundToGame(round: Round)
    fun observeGames(): Flow<List<Game>>
}