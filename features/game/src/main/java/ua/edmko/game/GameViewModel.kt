package ua.edmko.game

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ua.edmko.core.base.BaseViewModel
import ua.edmko.domain.entities.Player
import ua.edmko.domain.entities.Round
import ua.edmko.domain.interactor.AddRoundToGame
import ua.edmko.domain.interactor.ObserveGame
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getGame: ObserveGame,
    private val addRoundToGame: AddRoundToGame,
    private val gameNavigator: GameNavigator
) :
    BaseViewModel<GameViewState, GameEvent>() {

    init {
        viewState = GameViewState()
        val gameId: String = savedStateHandle["gameId"]
            ?: throw IllegalArgumentException("Game id must not be null")
        viewModelScope.launch {
            getGame.createObservable(ObserveGame.Params(gameId)).collect { game ->
                viewState = viewState.copy(
                    game = game,
                    currentRound = Round(
                        gameRoundId = game.gameSettings.id,
                        roundNum = 1
                    )
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
            is NavigateBack -> viewModelScope.launch { gameNavigator.back() }
            is NextRound -> nextRound()
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
        gameNavigator.toEnd(playerName)
    }

    private fun nextRound() {
        viewModelScope.launch {
            val currentRound = viewState.currentRound
            addRoundToGame.executeSync(AddRoundToGame.Params(currentRound))
            viewState = viewState.copy(
                currentRound = Round(
                    gameRoundId = viewState.game.gameSettings.id,
                    roundNum = currentRound.roundNum + 1,
                    )
            )
        }
    }

    private fun editScore(player: Player) {
        viewState = viewState.copy(isDialogShows = true, selectedPlayer = player)
    }

    private fun confirmEdition(score: Int) {
        val currentRound = viewState.currentRound.apply {
            result[viewState.selectedPlayer?.playerId ?: 0] = score
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