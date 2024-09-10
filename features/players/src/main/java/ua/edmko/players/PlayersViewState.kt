package ua.edmko.players

import ua.edmko.core.base.ViewState
import ua.edmko.domain.entities.Player

internal data class PlayersViewState(
    val dialog: Dialog? = null,
    val players: List<Player> = emptyList(),
) : ViewState

internal sealed interface Dialog

internal class EditPlayersName(val player: Player) : Dialog

internal class DeletePlayer(val player: Player) : Dialog

internal data object AddPlayer : Dialog
