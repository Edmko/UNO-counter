package ua.edmko.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ua.edmko.data.local.GameDao
import ua.edmko.data.local.entities.GameCrossRef
import ua.edmko.data.mappers.GameMapper
import ua.edmko.data.mappers.GameSettingsMapper
import ua.edmko.data.mappers.RoundMapper
import ua.edmko.domain.entities.Game
import ua.edmko.domain.entities.GameSettings
import ua.edmko.domain.entities.Round
import ua.edmko.domain.repository.GameRepository
import javax.inject.Inject

internal class GameRepositoryImpl @Inject constructor(
    private val gameDao: GameDao,
    private val gameMapper: GameMapper,
    private val gameSettingsMapper: GameSettingsMapper,
    private val roundMapper: RoundMapper,
) : GameRepository {

    override suspend fun createGame(gameSettings: GameSettings) {
        gameDao.insertGameSettings(gameSettingsMapper.map(gameSettings))
    }

    override suspend fun addPlayerToGame(
        playerId: Long,
        gameId: String,
    ) {
        val crossRef = GameCrossRef(playerId, gameId)
        gameDao.addPlayerToGame(crossRef)
    }

    override fun observeGameById(gameId: String): Flow<Game> {
        return gameDao.observeGameById(gameId).map {
            gameMapper.map(it)
        }
    }

    override suspend fun addRoundToGame(round: Round) {
        gameDao.addRoundToGame(roundMapper.map(round))
    }

    override fun observeGames(): Flow<List<Game>> {
        return gameDao.getAllGames().map { it.map(gameMapper::map) }
    }
}
