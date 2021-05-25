package ua.edmko.unocounter.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlayer(recipe: PlayerDatabase)

    @Query("Delete from players Where id =:id")
    suspend fun deletePlayerById(id: String)

    @Query("Select * from players")
    fun getAllPlayers(): Flow<List<PlayerDatabase>>

    @Query("Select * from players Where isSelected = 1")
    suspend fun getSelectedPlayers(): List<PlayerDatabase>

    @Update
    suspend fun updatePlayer(player: PlayerDatabase)
}