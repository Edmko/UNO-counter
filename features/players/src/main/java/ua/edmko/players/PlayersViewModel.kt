package ua.edmko.players

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ua.edmko.core.base.BaseViewModel
import ua.edmko.domain.entities.Player
import ua.edmko.domain.interactor.AddPlayer
import ua.edmko.domain.interactor.DeletePlayer
import ua.edmko.domain.interactor.ObservePlayers
import ua.edmko.domain.interactor.UpdatePlayer
import javax.inject.Inject

@HiltViewModel
class PlayersViewModel @Inject constructor(
    private val observePlayers: ObservePlayers,
    private val addPlayer: AddPlayer,
    private val updatePlayer: UpdatePlayer,
    private val deletePlayer: DeletePlayer,
    val navigator: PlayersNavigator
) : BaseViewModel<PlayersViewState, PlayersEvent>() {

    init {
        viewState = PlayersViewState()
        viewModelScope.launch {
            observePlayers.createObservable(Unit).collect { players ->
                viewState = viewState.copy(players = players)
            }
        }
    }

    override fun obtainEvent(viewEvent: PlayersEvent) {
        when (viewEvent) {
            is AddPlayerButton -> viewState = viewState.copy(editDialogShows = true)
            is CreatePlayer -> createPlayer(viewEvent.name)
            is UpdatePlayersSelection -> updatePlayer(viewEvent.player)
            is OnDeletePlayer -> onDeletePlayer(viewEvent.player)
            is NavigateBack -> navigateBack()
            is DismissDialog -> dismissDialog()
            is EditPlayer -> onUpdatePlayerName(viewEvent.player)
            is ChangePlayersName -> changePlayersName(viewEvent.name)
            is DeletePlayerEvent -> deletePlayer()
        }
    }

    private fun dismissDialog() {
        viewState = viewState.copy(
            editDialogShows = false,
            confirmationDialogShows = false,
            selectedPlayer = null
        )
    }

    private fun changePlayersName(name: String) {
        val player = viewState.selectedPlayer?.copy(name = name)
            ?: throw Exception("player must not be null")
        updatePlayer(player)
        viewState = viewState.copy(selectedPlayer = null, editDialogShows = false)
    }

    private fun onUpdatePlayerName(player: Player) {
        viewState = viewState.copy(editDialogShows = true, selectedPlayer = player)
    }

    private fun navigateBack() {
        viewModelScope.launch {
            navigator.back()
        }
    }

    private fun createPlayer(name: String) {
        viewModelScope.launch {
            addPlayer.executeSync(AddPlayer.Params(name))
            viewState = viewState.copy(editDialogShows = false)
        }
    }

    private fun onDeletePlayer(player: Player) {
        viewState = viewState.copy(selectedPlayer = player, confirmationDialogShows = true)
    }

    private fun deletePlayer() {
        viewModelScope.launch {
            val player = viewState.selectedPlayer?.playerId ?: throw Exception("Player must not be null")
            deletePlayer.executeSync(DeletePlayer.Params(player))
            viewState = viewState.copy(confirmationDialogShows = false, selectedPlayer = null)
        }
    }

    private fun updatePlayer(player: Player) {
        viewModelScope.launch {
            updatePlayer.executeSync(UpdatePlayer.Params(player))
        }
    }
}