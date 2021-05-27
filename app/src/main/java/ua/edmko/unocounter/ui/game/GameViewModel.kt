package ua.edmko.unocounter.ui.game

import dagger.hilt.android.lifecycle.HiltViewModel
import ua.edmko.unocounter.base.BaseViewModel
import ua.edmko.unocounter.domain.entities.Player
import ua.edmko.unocounter.navigation.NavigationManager
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(navigationManager: NavigationManager) :
    BaseViewModel<GameViewState, GameEvent>(navigationManager) {

    init {
        viewState = GameViewState()
    }

    override fun obtainEvent(viewEvent: GameEvent) {
        when (viewEvent) {
            is ConfirmEdition -> confirmEdition(viewEvent.score)
            is DismissDialog -> viewState = viewState.copy(isDialogShows = false)
            is EditScore -> editScore(viewEvent.player)
        }
    }

    private fun editScore(player: Player) {
        viewState = viewState.copy(isDialogShows = true, selectedPlayer = player)
    }

    private fun confirmEdition(score: Int) {
        val currentRound = viewState.currentRound.also { it.result[viewState.selectedPlayer?.playerId ?: 0] = score }
        viewState = viewState.copy(isDialogShows = false, currentRound = currentRound)
    }
}