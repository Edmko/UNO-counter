package ua.edmko.game

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
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
internal class GameViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getGame: ObserveGame,
    private val addRoundToGame: AddRoundToGame,
    private val gameNavigator: GameNavigator,
) : BaseViewModel<GameViewState, GameEvent>() {

    override fun initialize() {
        super.initialize()
        viewState = GameViewState()
        viewModelScope.launch {
            val gameId: String = savedStateHandle.toRoute<GameScreenRoute>().gameId
            getGame
                .createObservable(ObserveGame.Params(gameId))
                .collect { game ->
                    viewState = viewState.copy(
                        game = game,
                        currentRound = Round(
                            gameRoundId = game.gameSettings.id,
                            roundNum = 1,
                        ),
                    )
                    checkIsGameEnd()
                }
        }
    }

    override fun obtainEvent(viewEvent: GameEvent) {
        when (viewEvent) {
            is ConfirmEdition -> confirmEdition(viewEvent.score)
            is DismissDialog -> viewState = viewState.copy(editPlayerState = null)
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
        gameNavigator.toEnd(playerName)
    }

    private fun nextRound() {
        viewModelScope.launch {
            val currentRound = viewState.currentRound
            addRoundToGame(AddRoundToGame.Params(currentRound)).collect()
            viewState = viewState.copy(
                currentRound = Round(
                    gameRoundId = viewState.game.gameSettings.id,
                    roundNum = currentRound.roundNum + 1,
                ),
            )
        }
    }

    private fun editScore(player: Player) {
        viewState = viewState.copy(editPlayerState = EditPlayerState(player))
    }

    private fun confirmEdition(score: String) {
        val scoreNum = score.toIntOrNull() ?: 0
        val currentRound = viewState.currentRound.apply {
            result[viewState.editPlayerState?.player?.playerId ?: 0] = scoreNum
        }
        viewState = viewState.copy(editPlayerState = null, currentRound = currentRound)
    }

    private fun checkIsGameEnd() {
        viewState.game.let { game ->
            val (leader, score) = game.getLeader()
            if (score >= game.gameSettings.goal) endGame(leader.name)
        }
    }
}
