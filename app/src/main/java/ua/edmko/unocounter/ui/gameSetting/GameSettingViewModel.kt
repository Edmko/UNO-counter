package ua.edmko.unocounter.ui.gameSetting

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ua.edmko.unocounter.base.BaseViewModel
import ua.edmko.unocounter.domain.entities.GameSettings
import ua.edmko.unocounter.domain.entities.GameType
import ua.edmko.unocounter.domain.interactor.CreateGame
import ua.edmko.unocounter.domain.interactor.GetSelectedPlayers
import ua.edmko.unocounter.navigation.NavigationDirections
import ua.edmko.unocounter.navigation.NavigationManager
import javax.inject.Inject

@HiltViewModel
class GameSettingViewModel @Inject constructor(
    private val getPlayers: GetSelectedPlayers,
    private val createGame: CreateGame,
    navigationManager: NavigationManager
) :
    BaseViewModel<GameSettingViewState, GameSettingEvent>(navigationManager) {

    init {
        viewState = GameSettingViewState()
    }

    override fun obtainEvent(viewEvent: GameSettingEvent) {
        when (viewEvent) {
            is ChangeGoal -> setGoal(viewEvent.goal)
            is OnGoalClickEvent -> changeGoal()
            is EditPlayers -> viewModelScope.launch { navigateTo(NavigationDirections.players) }
            is DismissDialog -> viewState = viewState.copy(dialogShows = false)
            is StartGame -> startGame()
        }
    }

    private fun startGame() {
        viewModelScope.launch {
            val settings = GameSettings(type = GameType.CLASSIC, goal = viewState.goal)
            createGame.executeSync(CreateGame.Params(settings))
            navigateTo(NavigationDirections.game(settings.gameSettingsId))
        }
    }

    private fun setGoal(goal: Int) {
        viewState = viewState.copy(dialogShows = false, goal = goal)
    }

    private fun changeGoal() {
        viewState = viewState.copy(dialogShows = true)
    }

    fun fetchPlayers() {
        viewModelScope.launch {
            viewState = viewState.copy(players = getPlayers.executeSync(Unit))
        }
    }
}