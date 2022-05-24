package com.edmko.setup

import androidx.lifecycle.viewModelScope
import com.edmko.setup.SetupViewState.DialogType
import dagger.hilt.android.lifecycle.HiltViewModel
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
) : BaseViewModel<SetupViewState, GameSettingEvent>() {

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
            is ChangeGoal ->  viewState = viewState.copy(dialog = null, goal = viewEvent.goal)
            is OnGoalClickEvent ->  viewState = viewState.copy(dialog = DialogType.Edit)
            is EditPlayers -> navigator.toPlayers()
            is DismissDialog -> viewState = viewState.copy(dialog = null)
            is StartGame -> startGame()
            is OnTypeClickEvent -> viewState = viewState.copy(dialog = DialogType.Type)
            is SetGameType -> setGameType(viewEvent.gameType)
        }
    }

    private fun setGameType(gameType: GameType) {
        viewState = viewState.copy(dialog = null, gameType = gameType)
    }

    private fun startGame() {
        viewModelScope.launch {
            val settings = GameSettings(type = viewState.gameType, goal = viewState.goal)
            createGame.executeSync(CreateGame.Params(settings))
            navigator.toGame(settings.id)
        }
    }
}