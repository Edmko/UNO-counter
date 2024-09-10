package com.edmko.setup

import androidx.lifecycle.viewModelScope
import com.edmko.setup.SetupViewState.DialogType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import ua.edmko.core.base.BaseViewModel
import ua.edmko.domain.entities.GameSettings
import ua.edmko.domain.entities.GameType
import ua.edmko.domain.interactor.CreateGame
import ua.edmko.domain.interactor.InvokeSuccess
import ua.edmko.domain.interactor.ObserveSelectedPlayers
import javax.inject.Inject

@HiltViewModel
internal class SetupViewModel @Inject constructor(
    private val createGame: CreateGame,
    private val observeSelectedPlayers: ObserveSelectedPlayers,
    private val navigator: SetupNavigator,
) : BaseViewModel<SetupViewState, GameSettingEvent>() {

    override fun initialize() {
        super.initialize()
        viewState = SetupViewState()
        viewModelScope.smartLaunch {
            observeSelectedPlayers
                .createObservable(Unit)
                .catch { emit(emptyList()) }
                .collect { players -> viewState = viewState.copy(players = players) }
        }
    }

    override fun obtainEvent(viewEvent: GameSettingEvent) {
        when (viewEvent) {
            is ChangeGoal -> viewState = viewState.copy(dialog = null, goal = viewEvent.goal)
            is OnGoalClickEvent -> viewState = viewState.copy(dialog = DialogType.EditGoal)
            is EditPlayers -> navigator.toPlayers()
            is DismissDialog -> viewState = viewState.copy(dialog = null)
            is StartGame -> startGame()
            is OnTypeClickEvent -> viewState = viewState.copy(dialog = DialogType.GameType)
            is SetGameType -> setGameType(viewEvent.gameType)
            is OnSettingsClick -> navigator.toSettings()
        }
    }

    private fun setGameType(gameType: GameType) {
        viewState = viewState.copy(dialog = null, gameType = gameType)
    }

    private fun startGame() = viewModelScope.launch {
        val settings = GameSettings(type = viewState.gameType, goal = viewState.goal)
        createGame(CreateGame.Params(settings)).collect {
            when (it) {
                InvokeSuccess -> navigator.toGame(settings.id)
                else -> Unit
            }
        }
    }
}
