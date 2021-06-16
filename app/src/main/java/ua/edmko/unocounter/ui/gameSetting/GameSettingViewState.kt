package ua.edmko.unocounter.ui.gameSetting

import ua.edmko.unocounter.base.ViewState
import ua.edmko.unocounter.domain.entities.GameType
import ua.edmko.unocounter.domain.entities.Player

data class GameSettingViewState(
    val goal: Int = 500,
    val dialogShows: Boolean = false,
    val typeDialogShows: Boolean = false,
    val players: List<Player> = emptyList(),
    val gameType: GameType = GameType.CLASSIC
) : ViewState