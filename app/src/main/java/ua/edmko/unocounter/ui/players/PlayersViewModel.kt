package ua.edmko.unocounter.ui.players

import dagger.hilt.android.lifecycle.HiltViewModel
import ua.edmko.unocounter.base.BaseViewModel
import ua.edmko.unocounter.navigation.NavigationManager
import javax.inject.Inject

@HiltViewModel
class PlayersViewModel @Inject constructor(navigationManager: NavigationManager) :
    BaseViewModel<PlayersViewState, PlayersAction, PlayersEvent>(navigationManager) {
    init {
        viewState = PlayersViewState()
    }

    override fun obtainEvent(viewEvent: PlayersEvent) {
        when (viewEvent) {
            is AddPlayerButton -> viewState = viewState.copy(isDialogShows = true)
            is CreatePlayer -> createPlayer(viewEvent.name)
        }
    }

    private fun createPlayer(name: String) {
        viewState = viewState.copy(isDialogShows = false)
    }
}