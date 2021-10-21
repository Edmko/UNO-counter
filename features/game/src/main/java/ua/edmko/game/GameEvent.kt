package ua.edmko.game

import ua.edmko.domain.entities.Player
import ua.edmko.core.base.Event

sealed class GameEvent: Event
class EditScore(val player: Player): GameEvent()
class ConfirmEdition(val score: Int): GameEvent()
object DismissDialog: GameEvent()
object NavigateBack: GameEvent()
object NextRound : GameEvent()
class SetWinner(val player: Player): GameEvent()
class EndGame(val winnerName: String): GameEvent()