package ua.edmko.unocounter.ui.players

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import ua.edmko.unocounter.base.BaseViewModel
import ua.edmko.unocounter.domain.entities.Player
import ua.edmko.unocounter.domain.interactor.AddPlayer
import ua.edmko.unocounter.domain.interactor.DeletePlayer
import ua.edmko.unocounter.domain.interactor.GetPlayers
import ua.edmko.unocounter.domain.interactor.UpdatePlayer
import ua.edmko.unocounter.navigation.NavigationManager
import java.util.*
import javax.inject.Inject

@HiltViewModel
class PlayersViewModel @Inject constructor(
    private val getPlayers: GetPlayers,
    private val addPlayer: AddPlayer,
    private val updatePlayer: UpdatePlayer,
    private val deletePlayer: DeletePlayer,
    navigationManager: NavigationManager
) :
    BaseViewModel<PlayersViewState, PlayersAction, PlayersEvent>(navigationManager) {
    init {
        viewState = PlayersViewState()
        fetchPlayers()
    }

    override fun obtainEvent(viewEvent: PlayersEvent) {
        when (viewEvent) {
            is AddPlayerButton -> viewState = viewState.copy(isDialogShows = true)
            is CreatePlayer -> createPlayer(viewEvent.name)
        }
    }

    private fun createPlayer(name: String) {
        viewModelScope.launch {
            val player = Player(
                id = UUID.randomUUID().toString(),
                name = name
            )
            addPlayer.executeSync(AddPlayer.Params(player))
            val players = viewState.players.toMutableList().also {
                it.add(player)
            }
            viewState = viewState.copy(isDialogShows = false, players = players)
        }

    }

    private fun fetchPlayers(){
        viewModelScope.launch {
            viewState = viewState.copy(players = getPlayers.executeSync(Unit))
        }
    }
}