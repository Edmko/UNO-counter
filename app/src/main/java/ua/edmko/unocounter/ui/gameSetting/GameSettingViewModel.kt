package ua.edmko.unocounter.ui.gameSetting

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ua.edmko.unocounter.base.BaseViewModel
import ua.edmko.unocounter.navigation.NavigationDirections
import ua.edmko.unocounter.navigation.NavigationManager
import javax.inject.Inject

@HiltViewModel
class GameSettingViewModel @Inject constructor(private val navigationManager: NavigationManager) :
    BaseViewModel<GameSettingViewState, GameAction, GameEvent>(navigationManager) {

    init {
        viewState = GameSettingViewState()
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

    val players = listOf(
        "John Smith",
        "Dali Bali",
        "Man Quite",
        "Vasya",
        "John Smith",
        "Dali Bali",
        "Man Quite",
        "Vasya",
        "John Smith",
        "Dali Bali",
        "Man Quite",
        "Vasya"
    )
}