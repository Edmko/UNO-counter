package ua.edmko.game

import ua.edmko.core.base.ViewState
import ua.edmko.domain.entities.Game
import ua.edmko.domain.entities.Player
import ua.edmko.domain.entities.Round

internal data class GameViewState(
    val game: Game = Game.getEmptyGame(),
    val isDialogShows: Boolean = false,
    val selectedPlayer: Player? = null,
    val currentRound: Round = Round.empty,
) : ViewState {
    companion object {
        val Stub = GameViewState(
            Game.getGameStub(),
        )
    }
}
