package ua.edmko.unocounter.ui.players

import ua.edmko.unocounter.base.ViewState
import ua.edmko.unocounter.domain.entities.Player

data class PlayersViewState(
    var editDialogShows: Boolean = false,
    var confirmationDialogShows: Boolean = false,
    var selectedPlayer: Player? = null,
    var players: List<Player> = emptyList()
): ViewState