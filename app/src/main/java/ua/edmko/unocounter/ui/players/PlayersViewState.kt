package ua.edmko.unocounter.ui.players

import ua.edmko.unocounter.base.ViewState
import ua.edmko.unocounter.domain.entities.Player

data class PlayersViewState(
    var isDialogShows: Boolean = false,
    var players: List<Player> = emptyList()
): ViewState