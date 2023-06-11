package com.edmko.setup

import ua.edmko.core.base.Event
import ua.edmko.domain.entities.GameType

internal sealed class GameSettingEvent : Event
internal class ChangeGoal(val goal: Int) : GameSettingEvent()
internal object OnGoalClickEvent : GameSettingEvent()
internal object OnTypeClickEvent : GameSettingEvent()
internal object EditPlayers : GameSettingEvent()
internal object DismissDialog : GameSettingEvent()
internal class SetGameType(val gameType: GameType) : GameSettingEvent()
internal object StartGame : GameSettingEvent()
