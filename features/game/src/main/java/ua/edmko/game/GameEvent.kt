package ua.edmko.game

import ua.edmko.core.base.Event
import ua.edmko.domain.entities.Player

sealed class GameEvent : Event
class EditScore(val player: Player) : GameEvent()
class ConfirmEdition(val score: Int) : GameEvent()
object DismissDialog : GameEvent()
object NavigateBack : GameEvent()
object NextRound : GameEvent()
class SetWinner(val player: Player) : GameEvent()
