package ua.edmko.players

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ua.edmko.core.base.BaseViewModel
import ua.edmko.domain.entities.Player
import ua.edmko.domain.interactor.AddPlayer
import ua.edmko.domain.interactor.DeletePlayer
import ua.edmko.domain.interactor.ObservePlayers
import ua.edmko.domain.interactor.UpdatePlayer
import javax.inject.Inject

@HiltViewModel
internal class PlayersViewModel @Inject constructor(
    private val observePlayers: ObservePlayers,
    private val addPlayer: AddPlayer,
    private val updatePlayer: UpdatePlayer,
    private val deletePlayer: DeletePlayer,
    private val navigator: PlayersNavigator,
) : BaseViewModel<PlayersViewState, PlayersEvent>() {

    override fun initialize() {
        super.initialize()
        viewState = PlayersViewState()
        viewModelScope.launch {
            observePlayers.createObservable(Unit).collect { players ->
                viewState = viewState.copy(players = players)
            }
        }
    }

    override fun obtainEvent(viewEvent: PlayersEvent) {
        when (viewEvent) {
            is AddPlayerButton -> viewState = viewState.copy(dialog = AddPlayer)
            is CreatePlayer -> createPlayer(viewEvent.name)
            is UpdatePlayersSelection -> updatePlayer(viewEvent.player)
            is OnDeletePlayer -> onDeletePlayer(viewEvent.player)
            is NavigateBack -> navigator.back()
            is DismissDialog -> dismissDialog()
            is EditPlayer -> onUpdatePlayerName(viewEvent.player)
            is ChangePlayersName -> changePlayersName(viewEvent.player, viewEvent.name)
            is DeletePlayerEvent -> deletePlayer(viewEvent.player)
        }
    }

    private fun dismissDialog() {
        viewState = viewState.copy(dialog = null)
    }

    private fun changePlayersName(player: Player, name: String) {
        updatePlayer(player.copy(name = name))
        dismissDialog()
    }

    private fun onUpdatePlayerName(player: Player) {
        viewState = viewState.copy(dialog = EditPlayersName(player))
    }

    private fun createPlayer(name: String) = viewModelScope.launch {
        addPlayer.executeSync(AddPlayer.Params(name))
        dismissDialog()
    }

    private fun onDeletePlayer(player: Player) {
        viewState = viewState.copy(dialog = DeletePlayer(player))
    }

    private fun deletePlayer(player: Player) = viewModelScope.launch {
        deletePlayer.executeSync(DeletePlayer.Params(player.playerId))
        dismissDialog()
    }

    private fun updatePlayer(player: Player) = viewModelScope.launch {
        updatePlayer.executeSync(UpdatePlayer.Params(player))
    }
}
