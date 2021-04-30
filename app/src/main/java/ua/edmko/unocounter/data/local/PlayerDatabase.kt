package ua.edmko.unocounter.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ua.edmko.unocounter.domain.entities.Player

@Entity(tableName = "players")
class PlayerDatabase (
    @PrimaryKey val id: String,
    @ColumnInfo(name = "name")val name: String,
    @ColumnInfo(name = "isSelected")val isSelected: Boolean
)

fun PlayerDatabase.asDomain(): Player {
    return Player(id, name, isSelected)
}