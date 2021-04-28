package ua.edmko.unocounter.ui.players

import dagger.hilt.android.lifecycle.HiltViewModel
import ua.edmko.unocounter.base.BaseViewModel
import ua.edmko.unocounter.navigation.NavigationManager
import javax.inject.Inject

@HiltViewModel
class PlayersViewModel @Inject constructor(navigationManager: NavigationManager): BaseViewModel<PlayersViewState, PlayersAction, PlayersEvent>(navigationManager) {

    override fun obtainEvent(viewEvent: PlayersEvent) {

    }
}