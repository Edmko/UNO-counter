package com.edmko.setup

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ua.edmko.core.base.BaseViewModel
import ua.edmko.domain.entities.GameSettings
import ua.edmko.domain.entities.GameType
import ua.edmko.domain.interactor.CreateGame
import ua.edmko.domain.interactor.ObserveSelectedPlayers
import javax.inject.Inject

@HiltViewModel
class SetupViewModel @Inject constructor(
    private val createGame: CreateGame,
    private val observeSelectedPlayers: ObserveSelectedPlayers,
    private val navigator: SetupNavigator
) : BaseViewModel<SetupViewState, GameSettingEvent>(){

    init {
        viewState = SetupViewState()
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
            is EditPlayers -> viewModelScope.launch { navigator.toPlayers() }
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
            val settings = GameSettings(type = viewState.gameType, goal = viewState.goal)
            createGame.executeSync(CreateGame.Params(settings))
            navigator.toGame(settings.id)
        }
    }

    private fun setGoal(goal: Int) {
        viewState = viewState.copy(dialogShows = false, goal = goal)
    }

    private fun changeGoal() {
        viewState = viewState.copy(dialogShows = true)
    }
}