package ua.edmko.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ua.edmko.data.local.entities.PlayerLocal

@Dao
internal interface PlayersDao {

    @Insert
    suspend fun insertPlayer(recipe: PlayerLocal)

    @Query("Delete from players Where playerId =:id")
    suspend fun deletePlayerById(id: Long)

    @Query("Select * from players")
    fun observePlayers(): Flow<List<PlayerLocal>>

    @Query("Select * from players Where isSelected = 1")
    suspend fun getSelectedPlayers(): List<PlayerLocal>

    @Update
    suspend fun updatePlayer(player: PlayerLocal)
}
