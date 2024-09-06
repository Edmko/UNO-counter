package ua.edmko.players

import ua.edmko.core.base.Event
import ua.edmko.domain.entities.Player

internal sealed class PlayersEvent : Event

internal data object AddPlayerButton : PlayersEvent()

internal class CreatePlayer(val name: String) : PlayersEvent()

internal class UpdatePlayersSelection(val player: Player) : PlayersEvent()

internal class OnDeletePlayer(val player: Player) : PlayersEvent()

internal data object DeletePlayerEvent : PlayersEvent()

internal data object NavigateBack : PlayersEvent()

internal data object DismissDialog : PlayersEvent()

internal class EditPlayer(val player: Player) : PlayersEvent()

internal class ChangePlayersName(val name: String) : PlayersEvent()
