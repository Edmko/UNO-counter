package ua.edmko.unocounter.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ua.edmko.unocounter.domain.entities.Player

@Dao
interface PlayersDao {

    @Insert
    suspend fun insertPlayer(recipe: Player)

    @Query("Delete from players Where playerId =:id")
    suspend fun deletePlayerById(id: Long)

    @Query("Select * from players")
    fun getAllPlayers(): Flow<List<Player>>

    @Query("Select * from players Where isSelected = 1")
    suspend fun getSelectedPlayers(): List<Player>

    @Update
    suspend fun updatePlayer(player: Player)
}