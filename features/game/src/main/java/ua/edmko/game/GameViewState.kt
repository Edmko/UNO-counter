package ua.edmko.game

import androidx.compose.runtime.Immutable
import ua.edmko.core.base.ViewState
import ua.edmko.domain.entities.Game
import ua.edmko.domain.entities.Player
import ua.edmko.domain.entities.Round

@Immutable
internal data class GameViewState(
    val game: Game = Game.EMPTY,
    val editPlayerState: EditPlayerState? = null,
    val currentRound: Round = Round.EMPTY,
) : ViewState {
    companion object {
        val STUB = GameViewState(Game.STUB)
    }
}

internal data class EditPlayerState(val player: Player)
