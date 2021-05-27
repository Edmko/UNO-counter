package ua.edmko.unocounter.ui.game

import ua.edmko.unocounter.base.ViewState
import ua.edmko.unocounter.domain.entities.Game
import ua.edmko.unocounter.domain.entities.Player
import ua.edmko.unocounter.domain.entities.Round

data class GameViewState(
    val game: Game = Game.getGameStub(),
    val isDialogShows: Boolean = false,
    val selectedPlayer : Player? = null,
    val currentRound: Round = Round(gameRoundId = game.gameSettings.gameSettingsId)
): ViewState