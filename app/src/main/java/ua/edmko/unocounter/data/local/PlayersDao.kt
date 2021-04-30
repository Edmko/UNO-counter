package ua.edmko.unocounter.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PlayersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlayer(recipe: PlayerDatabase)

    @Query("Delete from players Where id =:id")
    suspend fun deletePlayerById(id: String)

    @Query("Select * from players")
    suspend fun getAllPlayers(): List<PlayerDatabase>

    @Query("Select * from players Where isSelected = 1")
    suspend fun getSelectedPlayers(): List<PlayerDatabase>
}