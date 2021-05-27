package ua.edmko.unocounter.ui.gameSetting

import ua.edmko.unocounter.base.Event

sealed class GameSettingEvent: Event
class ChangeGoal(val goal: Int): GameSettingEvent()
object OnGoalClickEvent: GameSettingEvent()
object EditPlayers: GameSettingEvent()
object DismissDialog: GameSettingEvent()
object StartGame: GameSettingEvent()
