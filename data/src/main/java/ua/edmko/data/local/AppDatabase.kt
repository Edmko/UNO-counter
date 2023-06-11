package ua.edmko.data.local

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ua.edmko.data.local.entities.GameCrossRef
import ua.edmko.data.local.entities.GameSettingsLocal
import ua.edmko.data.local.entities.PlayerLocal
import ua.edmko.data.local.entities.RoundLocal
import ua.edmko.data.local.entities.RoundsConverter

@Database(
    entities = [PlayerLocal::class, GameSettingsLocal::class, GameCrossRef::class, RoundLocal::class],
    version = 2,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(
            from = 1,
            to = 2,
        ),
    ],
)
@TypeConverters(RoundsConverter::class)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun playersDao(): PlayersDao
    abstract fun gameDao(): GameDao
}
