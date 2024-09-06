package ua.edmko.settings

import ua.edmko.core.base.Event
import ua.edmko.domain.entities.Theme

internal sealed interface SettingsEvent : Event

data object ThemeClick : SettingsEvent

data class SetTheme(val theme: Theme) : SettingsEvent

data object PrivacyClick : SettingsEvent
