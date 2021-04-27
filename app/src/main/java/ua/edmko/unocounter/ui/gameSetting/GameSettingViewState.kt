package ua.edmko.unocounter.ui.gameSetting

import ua.edmko.unocounter.base.ViewState

data class GameSettingViewState(
    var goal: Int = -1,
    var dialogShows: Boolean = false
): ViewState