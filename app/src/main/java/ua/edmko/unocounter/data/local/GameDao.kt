package ua.edmko.unocounter.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ua.edmko.unocounter.domain.entities.Game
import ua.edmko.unocounter.domain.entities.GameCrossRef
import ua.edmko.unocounter.domain.entities.GameSettings
import ua.edmko.unocounter.domain.entities.Round

@Dao
interface GameDao {

    @Insert
    suspend fun insertGameSettings(gameSettings: GameSettings)

    @Insert
    suspend fun addPlayerToGame(gameCrossRef: GameCrossRef)

    @Transaction
    @Query("SELECT * FROM game_settings WHERE gameSettingsId = :id")
    fun observeGameById(id: String): Flow<Game>

    @Insert
    suspend fun addRoundToGame(round: Round)

    @Transaction
    @Query("SELECT * FROM game_settings")
    fun getAllGames(): Flow<List<Game>>
}