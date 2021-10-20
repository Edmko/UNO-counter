package ua.edmko.unocounter.ui.game

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ua.edmko.unocounter.base.BaseViewModel
import ua.edmko.unocounter.domain.entities.Player
import ua.edmko.unocounter.domain.entities.Round
import ua.edmko.unocounter.domain.interactor.AddRoundToGame
import ua.edmko.unocounter.domain.interactor.ObserveGame
import ua.edmko.navigation.NavigationDirections
import ua.edmko.navigation.NavigationManager
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val getGame: ObserveGame,
    private val addRoundToGame: AddRoundToGame,
    navigationManager: NavigationManager
) :
    BaseViewModel<GameViewState, GameEvent>(navigationManager) {

    init {
        Log.d("endGame", "init")
        viewState = GameViewState()

    }

    fun fetchGame(gameId: String) {
        viewModelScope.launch {
            getGame.createObservable(ObserveGame.Params(gameId)).collect { game ->
                viewState = viewState.copy(
                    game = game,
                    currentRound = Round(gameRoundId = game.gameSettings.gameSettingsId)
                )
                checkIsGameEnd()
            }

        }
    }

    override fun obtainEvent(viewEvent: GameEvent) {
        when (viewEvent) {
            is ConfirmEdition -> confirmEdition(viewEvent.score)
            is DismissDialog -> viewState = viewState.copy(isDialogShows = false)
            is EditScore -> editScore(viewEvent.player)
            is NavigateBack -> viewModelScope.launch { navigateTo(NavigationDirections.back) }
            is NextRound -> nextRound()
            is EndGame -> endGame(viewEvent.winnerName)
            is SetWinner -> setWinner(viewEvent.player.playerId)
        }
    }

    private fun setWinner(playerId: Long) {
        val result = viewState.currentRound.result.apply {
            this[playerId] = 0
        }
        val round = viewState.currentRound.copy(winner = playerId, result = result)

        viewState = viewState.copy(currentRound = round)
    }

    private fun endGame(playerName: String) {
        Log.d("end game", playerName)
        viewModelScope.launch { navigateTo(NavigationDirections.gameEnd(playerName)) }
    }

    private fun nextRound() {
        viewModelScope.launch {
            val currentRound = viewState.currentRound
            addRoundToGame.executeSync(AddRoundToGame.Params(currentRound))
            viewState = viewState.copy(
                currentRound = Round(
                    gameRoundId = viewState.game.gameSettings.gameSettingsId,
                    roundNum = currentRound.roundNum + 1
                )
            )
        }
    }

    private fun editScore(player: Player) {
        viewState = viewState.copy(isDialogShows = true, selectedPlayer = player)
    }

    private fun confirmEdition(score: Int) {
        val currentRound = viewState.currentRound.also {
            it.result[viewState.selectedPlayer?.playerId ?: 0] = score
        }
        viewState = viewState.copy(isDialogShows = false, currentRound = currentRound)
    }

    private fun checkIsGameEnd() {
        viewState.game.let { game ->
            val (leader, score) = game.getLeader()
            Log.d("end game", "leader = ${leader.name}, score = $score")
            if (score >= game.gameSettings.goal) endGame(leader.name)
        }
    }


}