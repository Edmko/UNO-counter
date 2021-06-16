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
    val rounds: List<Round> = emptyList(),
) {

    fun calculatePlayersTotal(): Map<Player, Int> {
        val total: MutableMap<Player, Int> = mutableMapOf()
        players.forEach { player ->
            var playersTotal = 0
            rounds.forEach { round ->
                playersTotal += round.result[player.playerId] ?: 0
            }
            total[player] = playersTotal
        }
        return total.toMap()
    }

    fun getLeader(): Pair<Player, Int>{
        return calculatePlayersTotal().maxByOrNull {it.value}?.toPair()?: throw noPlayersFoundException
    }

    companion object {
        val noPlayersFoundException = Exception("No players found")
        fun getGameStub() = Game(
            gameSettings = getGameSettingsStub(),
            players = getPlayersStub()
        )

        fun getEmptyGame() = Game(
            getGameSettingsStub(),
            emptyList()
        )
    }
}

@Entity(tableName = "game_settings")
data class GameSettings(
    val type: GameType,
    val goal: Int,
    @PrimaryKey val gameSettingsId: String = UUID.randomUUID().toString()
) {
    companion object {
        fun getGameSettingsStub() = GameSettings(type = GameType.CLASSIC, goal = 500)
    }
}

enum class GameType(val title : String) { CLASSIC("Classic"), COLLECTIVE("Collective") }

@Entity(primaryKeys = ["playerId", "gameSettingsId"])
data class GameCrossRef(
    val playerId: Long,
    val gameSettingsId: String
)