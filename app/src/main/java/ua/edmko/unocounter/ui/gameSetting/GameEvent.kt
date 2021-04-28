package ua.edmko.unocounter.ui.gameSetting

import ua.edmko.unocounter.base.Event

sealed class GameEvent: Event
class ChangeGoal(val goal: Int): GameEvent()
object OnGoalClickEvent: GameEvent()
object EditPlayers: GameEvent()
