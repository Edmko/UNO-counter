package ua.edmko.testing

import ua.edmko.domain.entities.Game
import ua.edmko.domain.entities.GameSettings
import ua.edmko.domain.entities.GameType
import ua.edmko.domain.entities.Player
import java.util.UUID

val gameId = UUID.randomUUID().toString()

val gameSettings = GameSettings(GameType.CLASSIC, 200, gameId)

val selectedPlayersList = listOf(
    Player(266542, "John Smith"),
    Player(254153, "Dali Bali"),
)

val game = Game(
    gameSettings = gameSettings,
    players = selectedPlayersList,
    rounds = emptyList(),
)

val firstPlayerFromGame = game.players.first()
