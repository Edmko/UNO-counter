package com.edmko.setup

import ua.edmko.core.base.ViewState
import ua.edmko.domain.entities.GameType
import ua.edmko.domain.entities.Player
import ua.edmko.domain.entities.Player.Companion.STUB
import ua.edmko.domain.entities.ResultState

internal data class SetupViewState(
    val goal: Int = 500,
    val dialog: DialogType? = null,
    val players: ResultState<List<Player>> = ResultState.Loading,
    val gameType: GameType = GameType.CLASSIC,
) : ViewState {

    enum class DialogType { GameType, EditGoal }

    companion object {
        val Preview = SetupViewState(
            players = ResultState.Success(STUB),
        )
    }
}
