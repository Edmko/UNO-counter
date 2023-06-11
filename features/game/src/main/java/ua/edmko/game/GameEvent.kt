package ua.edmko.game

import ua.edmko.core.base.Event
import ua.edmko.domain.entities.Player

internal sealed class GameEvent : Event
internal class EditScore(val player: Player) : GameEvent()
internal class ConfirmEdition(val score: Int) : GameEvent()
internal object DismissDialog : GameEvent()
internal object NavigateBack : GameEvent()
internal object NextRound : GameEvent()
internal class SetWinner(val player: Player) : GameEvent()
