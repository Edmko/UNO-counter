package ua.edmko.settings

import ua.edmko.core.base.ViewState
import ua.edmko.domain.entities.Theme

internal data class SettingsViewState(
    val theme: Theme = Theme.LIGHT,
    val dialog: Dialog? = null,
) : ViewState

internal enum class Dialog { Theme }
