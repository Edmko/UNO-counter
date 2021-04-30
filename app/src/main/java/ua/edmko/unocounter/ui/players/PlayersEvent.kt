package ua.edmko.unocounter.ui.players

import ua.edmko.unocounter.base.Event

sealed class PlayersEvent: Event
object AddPlayerButton: PlayersEvent()
class CreatePlayer(val name: String): PlayersEvent()
