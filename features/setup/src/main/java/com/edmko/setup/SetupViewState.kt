package com.edmko.setup

import ua.edmko.core.base.ViewState
import ua.edmko.domain.entities.GameType
import ua.edmko.domain.entities.Player
import ua.edmko.domain.entities.Player.Companion.playersStubList

internal data class SetupViewState(
    val goal: Int = 500,
    val dialog: DialogType? = null,
    val players: List<Player> = emptyList(),
    val gameType: GameType = GameType.CLASSIC,
) : ViewState {

    enum class DialogType { Type, Edit }
    companion object {
        val Preview = SetupViewState(
            players = playersStubList,
        )
    }
}
