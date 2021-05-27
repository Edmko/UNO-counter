package ua.edmko.unocounter.ui.game

import ua.edmko.unocounter.base.Event
import ua.edmko.unocounter.domain.entities.Player

sealed class GameEvent: Event
class EditScore(val player: Player): GameEvent()
class ConfirmEdition(val score: Int): GameEvent()
object DismissDialog: GameEvent()
