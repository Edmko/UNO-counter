package com.edmko.setup

import ua.edmko.core.base.ViewState
import ua.edmko.domain.entities.GameType
import ua.edmko.domain.entities.Player

data class SetupViewState(
    val goal: Int = 500,
    val dialogShows: Boolean = false,
    val typeDialogShows: Boolean = false,
    val players: List<Player> = emptyList(),
    val gameType: GameType = GameType.CLASSIC
) : ViewState