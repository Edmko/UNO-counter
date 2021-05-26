package ua.edmko.unocounter.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ua.edmko.unocounter.domain.entities.*

@Database(
    entities = [Player::class, GameSettings::class, GameCrossRef::class, Round::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(RoundsConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun playersDao(): PlayersDao
    abstract fun gameDao(): GameDao
}