package ua.edmko.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation
import ua.edmko.domain.entities.GameType
import java.util.UUID

internal data class GameLocal(
    @Embedded val gameSettings: GameSettingsLocal,
    @Relation(
        parentColumn = "gameSettingsId",
        entityColumn = "playerId",
        associateBy = Junction(GameCrossRef::class),
    )
    val players: List<PlayerLocal>,

    @Relation(parentColumn = "gameSettingsId", entityColumn = "gameRoundId")
    val rounds: List<RoundLocal> = emptyList(),
) : DaoModel()

@Entity(tableName = "game_settings")
internal data class GameSettingsLocal(
    val type: GameType,
    val goal: Int,
    @PrimaryKey val gameSettingsId: String = UUID.randomUUID().toString(),
) : DaoModel()

@Entity(primaryKeys = ["playerId", "gameSettingsId"])
internal data class GameCrossRef(
    val playerId: Long,
    @ColumnInfo(index = true)
    val gameSettingsId: String,
)
