package ua.edmko.unocounter.domain.entities

import androidx.room.*
import ua.edmko.unocounter.domain.entities.GameSettings.Companion.getGameSettingsStub
import ua.edmko.unocounter.domain.entities.Player.Companion.getPlayersStub
import java.util.*

data class Game(
    @Embedded val gameSettings: GameSettings,
    @Relation(
        parentColumn = "gameSettingsId", entityColumn = "playerId",
        associateBy = Junction(GameCrossRef::class)
    )
    val players: List<Player>,

    @Relation(parentColumn = "gameSettingsId", entityColumn = "gameRoundId")
    val rounds: List<Round> = emptyList()
){
    companion object {
        fun getGameStub() = Game(
            gameSettings = getGameSettingsStub(),
            players = getPlayersStub()
        )
    }
}

@Entity(tableName = "game_settings")
data class GameSettings(
    val type: GameType,
    val goal: Int,
    @PrimaryKey val gameSettingsId: String = UUID.randomUUID().toString()
){
    companion object{
        fun getGameSettingsStub() = GameSettings(type = GameType.CLASSIC, goal = 500)
    }
}

enum class GameType { CLASSIC, COLLECTIVE }

@Entity(primaryKeys = ["playerId", "gameSettingsId"])
data class GameCrossRef(
    val playerId: Long,
    val gameSettingsId: String
)