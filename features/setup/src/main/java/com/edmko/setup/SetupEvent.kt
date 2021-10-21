package com.edmko.setup

import ua.edmko.domain.entities.GameType
import ua.edmko.core.base.Event

sealed class GameSettingEvent: Event
class ChangeGoal(val goal: Int): GameSettingEvent()
object OnGoalClickEvent: GameSettingEvent()
object OnTypeClickEvent: GameSettingEvent()
object EditPlayers: GameSettingEvent()
object DismissDialog: GameSettingEvent()
class SetGameType(val gameType: GameType): GameSettingEvent()
object StartGame: GameSettingEvent()
