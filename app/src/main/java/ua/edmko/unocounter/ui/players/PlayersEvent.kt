package ua.edmko.unocounter.ui.players

import ua.edmko.unocounter.base.Event
import ua.edmko.unocounter.domain.entities.Player

sealed class PlayersEvent: Event
object AddPlayerButton: PlayersEvent()
class CreatePlayer(val name: String): PlayersEvent()
class UpdatePlayersSelection(val player: Player): PlayersEvent()
class OnDeletePlayer(val player: Player): PlayersEvent()
object DeletePlayerEvent: PlayersEvent()
object NavigateBack: PlayersEvent()
object DismissDialog: PlayersEvent()
class EditPlayer(val player: Player): PlayersEvent()
class ChangePlayersName(val name: String): PlayersEvent()