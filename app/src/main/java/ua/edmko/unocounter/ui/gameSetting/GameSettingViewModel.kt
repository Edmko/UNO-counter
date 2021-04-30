package ua.edmko.unocounter.ui.gameSetting

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ua.edmko.unocounter.base.BaseViewModel
import ua.edmko.unocounter.domain.entities.Player
import ua.edmko.unocounter.domain.entities.Player.Companion.getPlayersStub
import ua.edmko.unocounter.navigation.NavigationDirections
import ua.edmko.unocounter.navigation.NavigationManager
import javax.inject.Inject

@HiltViewModel
class GameSettingViewModel @Inject constructor(private val navigationManager: NavigationManager) :
    BaseViewModel<GameSettingViewState, GameAction, GameEvent>(navigationManager) {

    init {
        viewState = GameSettingViewState(players = getPlayersStub())
    }

    override fun obtainEvent(viewEvent: GameEvent) {
        when (viewEvent) {
            is ChangeGoal -> setGoal(viewEvent.goal)
            is OnGoalClickEvent -> changeGoal()
            is EditPlayers -> viewModelScope.launch {
                navigationManager.navigate(
                    NavigationDirections.players
                )
            }
        }
    }

    private fun setGoal(goal: Int) {
        viewState = viewState.copy(dialogShows = false, goal = goal)
    }

    private fun changeGoal() {
        viewState = viewState.copy(dialogShows = true)
    }
}