package ua.edmko.players

import ua.edmko.core.base.ViewState
import ua.edmko.domain.entities.Player

data class PlayersViewState(
    var editDialogShows: Boolean = false,
    var confirmationDialogShows: Boolean = false,
    var selectedPlayer: Player? = null,
    var players: List<Player> = emptyList(),
) : ViewState
