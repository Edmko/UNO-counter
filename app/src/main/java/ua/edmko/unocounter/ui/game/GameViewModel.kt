package ua.edmko.unocounter.ui.game

import dagger.hilt.android.lifecycle.HiltViewModel
import ua.edmko.unocounter.base.BaseViewModel
import ua.edmko.unocounter.navigation.NavigationManager
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(navigationManager: NavigationManager) :
    BaseViewModel<GameViewState, GameEvent>(navigationManager) {

    override fun obtainEvent(viewEvent: GameEvent) {
    }
}