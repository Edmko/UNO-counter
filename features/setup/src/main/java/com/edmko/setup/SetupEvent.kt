package com.edmko.setup

import ua.edmko.core.base.Event
import ua.edmko.domain.entities.GameType

internal sealed interface GameSettingEvent : Event

internal class ChangeGoal(val goal: Int) : GameSettingEvent

internal data object OnGoalClickEvent : GameSettingEvent

internal data object OnTypeClickEvent : GameSettingEvent

internal data object EditPlayers : GameSettingEvent

internal data object DismissDialog : GameSettingEvent

internal class SetGameType(val gameType: GameType) : GameSettingEvent

internal data object StartGame : GameSettingEvent

internal data object OnSettingsClick : GameSettingEvent
