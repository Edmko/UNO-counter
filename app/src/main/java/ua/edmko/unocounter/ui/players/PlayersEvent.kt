package ua.edmko.unocounter.ui.players

import ua.edmko.unocounter.base.Event
import ua.edmko.unocounter.domain.entities.Player

sealed class PlayersEvent: Event
object AddPlayerButton: PlayersEvent()
class CreatePlayer(val name: String): PlayersEvent()
class UpdatePlayer(val player: Player): PlayersEvent()
class DeletePlayer(val player: Player): PlayersEvent()
