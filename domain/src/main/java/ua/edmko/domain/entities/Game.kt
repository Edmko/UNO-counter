package ua.edmko.domain.entities

import ua.edmko.domain.entities.GameSettings.Companion.getGameSettingsStub
import ua.edmko.domain.entities.Player.Companion.playersStubList
import java.util.UUID

data class Game(
    val gameSettings: GameSettings,
    val players: List<Player>,
    val rounds: List<Round>,
) : Entity {

    fun calculatePlayersTotal(): Map<Player, Int> {
        val total = when (gameSettings.type) {
            GameType.CLASSIC -> calculateClassicTotal()
            GameType.COLLECTIVE -> calculateCollectiveTotal()
        }
        return total.toMap()
    }

    private fun calculateCollectiveTotal(): Map<Player, Int> {
        val total: MutableMap<Player, Int> = mutableMapOf()
        players.forEach { player ->
            var playersTotal = 0
            rounds.forEach { round ->
                if (player.playerId == round.winner) {
                    round.result.forEach { (_, v) -> playersTotal += v }
                }
            }
            total[player] = playersTotal
        }
        return total
    }

    private fun calculateClassicTotal(): Map<Player, Int> {
        val total: MutableMap<Player, Int> = mutableMapOf()
        players.forEach { player ->
            var playersTotal = 0
            rounds.forEach { round ->
                playersTotal += round.result[player.playerId] ?: 0
            }
            total[player] = playersTotal
        }
        return total
    }

    fun getLeader(): Pair<Player, Int> {
        return calculatePlayersTotal().maxByOrNull { it.value }?.toPair()
            ?: throw noPlayersFoundException
    }

    companion object {
        val noPlayersFoundException = Exception("No players found")
        fun getGameStub() = Game(
            gameSettings = getGameSettingsStub(),
            players = playersStubList,
            emptyList(),
        )

        fun getEmptyGame() = Game(
            getGameSettingsStub(),
            emptyList(),
            emptyList(),
        )
    }
}

data class GameSettings(
    val type: GameType,
    val goal: Int,
    val id: String = UUID.randomUUID().toString(),
) : Entity {
    companion object {
        fun getGameSettingsStub() =
            GameSettings(id = "", type = GameType.CLASSIC, goal = 500)
    }
}

enum class GameType { CLASSIC, COLLECTIVE }
