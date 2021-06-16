package ua.edmko.unocounter.ui.gameSetting

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ua.edmko.unocounter.base.BaseViewModel
import ua.edmko.unocounter.domain.entities.GameSettings
import ua.edmko.unocounter.domain.entities.GameType
import ua.edmko.unocounter.domain.interactor.CreateGame
import ua.edmko.unocounter.domain.interactor.ObserveSelectedPlayers
import ua.edmko.unocounter.navigation.NavigationDirections
import ua.edmko.unocounter.navigation.NavigationManager
import javax.inject.Inject

@HiltViewModel
class GameSettingViewModel @Inject constructor(
    private val createGame: CreateGame,
    private val observeSelectedPlayers: ObserveSelectedPlayers,
    navigationManager: NavigationManager
) :
    BaseViewModel<GameSettingViewState, GameSettingEvent>(navigationManager) {

    init {
        viewState = GameSettingViewState()
        viewModelScope.smartLaunch {
            observeSelectedPlayers.createObservable(Unit).collect { players ->
                viewState = viewState.copy(players = players)
            }
        }
    }

    override fun obtainEvent(viewEvent: GameSettingEvent) {
        when (viewEvent) {
            is ChangeGoal -> setGoal(viewEvent.goal)
            is OnGoalClickEvent -> changeGoal()
            is EditPlayers -> viewModelScope.launch { navigateTo(NavigationDirections.players) }
            is DismissDialog -> viewState = viewState.copy(dialogShows = false)
            is StartGame -> startGame()
            is OnTypeClickEvent -> viewState = viewState.copy(typeDialogShows = true)
            is SetGameType -> setGameType(viewEvent.gameType)
        }
    }

    private fun setGameType(gameType: GameType) {
        viewState = viewState.copy(typeDialogShows = false, gameType = gameType)
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
}