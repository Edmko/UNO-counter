package ua.edmko.unocounter.data.repository

import kotlinx.coroutines.flow.Flow
import ua.edmko.unocounter.data.local.GameDao
import ua.edmko.unocounter.domain.entities.Game
import ua.edmko.unocounter.domain.entities.GameCrossRef
import ua.edmko.unocounter.domain.entities.GameSettings
import ua.edmko.unocounter.domain.entities.Round
import ua.edmko.unocounter.domain.repository.GameRepository
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(private val gameDao: GameDao) : GameRepository {
    override suspend fun createGame(gameSettings: GameSettings) {
        gameDao.insertGameSettings(gameSettings)
    }


    override suspend fun addPlayerToGame(playerId: Long, gameId: String) {
        val crossRef = GameCrossRef(playerId, gameId)
        gameDao.addPlayerToGame(crossRef)
    }

    override suspend fun getGameById(gameId: String): Game {
        return gameDao.getGameById(gameId)
    }

    override suspend fun addRoundToGame(round: Round) {
        gameDao.addRoundToGame(round)
    }

    override fun getGameList(): Flow<List<Game>> {
        return gameDao.getAllGames()
    }
}