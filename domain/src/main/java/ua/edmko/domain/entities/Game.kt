package ua.edmko.domain.entities

import ua.edmko.domain.orZero

data class Game(
    val gameSettings: GameSettings,
    val players: List<Player>,
    val rounds: List<Round>,
) : Entity {

    companion object {

        val STUB = Game(
            gameSettings = GameSettings.EMPTY,
            players = Player.STUB,
            rounds = emptyList(),
        )

        val EMPTY = Game(
            gameSettings = GameSettings.EMPTY,
            players = emptyList(),
            rounds = emptyList(),
        )
    }

    fun getLeader(): Pair<Player, Int> {
        return calculatePlayersTotal().maxByOrNull { it.value }?.toPair()
            ?: throw IllegalStateException("No players found")
    }

    fun calculatePlayersTotal(): Map<Player, Int> {
        return when (gameSettings.type) {
            GameType.CLASSIC -> calculateClassicTotal()
            GameType.COLLECTIVE -> calculateCollectiveTotal()
        }
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
            val playersTotal = rounds.fold(0) { acc, round ->
                acc + round.result[player.playerId].orZero()
            }
            total[player] = playersTotal
        }
        return total
    }
}
