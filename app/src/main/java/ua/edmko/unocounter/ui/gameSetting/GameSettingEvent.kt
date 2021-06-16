package ua.edmko.unocounter.ui.gameSetting

import ua.edmko.unocounter.base.Event
import ua.edmko.unocounter.domain.entities.GameType

sealed class GameSettingEvent: Event
class ChangeGoal(val goal: Int): GameSettingEvent()
object OnGoalClickEvent: GameSettingEvent()
object OnTypeClickEvent: GameSettingEvent()
object EditPlayers: GameSettingEvent()
object DismissDialog: GameSettingEvent()
class SetGameType(val gameType: GameType): GameSettingEvent()
object StartGame: GameSettingEvent()
