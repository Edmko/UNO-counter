package ua.edmko.unocounter.ui.gameSetting

import ua.edmko.unocounter.base.ViewState
import ua.edmko.unocounter.domain.entities.Player

data class GameSettingViewState(
    var goal: Int = 500,
    var dialogShows: Boolean = false,
    var players: List<Player> = emptyList()
) : ViewState