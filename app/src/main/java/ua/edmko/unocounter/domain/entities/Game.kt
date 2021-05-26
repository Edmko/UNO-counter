package ua.edmko.unocounter.domain.entities

import androidx.room.*

data class Game(
    @Embedded val gameSettings: GameSettings,
    @Relation(
        parentColumn = "gameSettingsId", entityColumn = "playerId",
        associateBy = Junction(GameCrossRef::class)
    )
    val players: List<Player>,

    @Relation(parentColumn = "gameSettingsId", entityColumn = "gameRoundId")
    val rounds: List<Round>
)

@Entity(tableName = "game_settings")
data class GameSettings(
    @PrimaryKey(autoGenerate = true) val gameSettingsId: Long,
    val type: GameType,
    val goal: Int
)

enum class GameType { CLASSIC, COLLECTIVE }

@Entity(primaryKeys = ["playerId", "gameSettingsId"])
data class GameCrossRef(
    val playerId: Long,
    val gameSettingsId: Long
)