package ua.edmko.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import ua.edmko.data.local.entities.GameCrossRef
import ua.edmko.data.local.entities.GameLocal
import ua.edmko.data.local.entities.GameSettingsLocal
import ua.edmko.data.local.entities.RoundLocal

@Dao
internal interface GameDao {

    @Insert
    suspend fun insertGameSettings(gameSettings: GameSettingsLocal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPlayersToGame(gameCrossRefs: List<GameCrossRef>)

    @Transaction
    @Query("SELECT * FROM game_settings WHERE gameSettingsId = :id")
    fun observeGameById(id: String): Flow<GameLocal>

    @Insert
    suspend fun addRoundToGame(round: RoundLocal)

    @Transaction
    @Query("SELECT * FROM game_settings")
    fun getAllGames(): Flow<List<GameLocal>>
}
