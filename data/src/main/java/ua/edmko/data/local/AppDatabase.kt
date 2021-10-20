package ua.edmko.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ua.edmko.data.local.GameDao
import ua.edmko.data.local.PlayersDao
import ua.edmko.data.local.entities.*

@Database(
    entities = [PlayerLocal::class, GameSettingsLocal::class, GameCrossRef::class, RoundLocal::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(RoundsConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun playersDao(): PlayersDao
    abstract fun gameDao(): GameDao
}