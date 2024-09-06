package ua.edmko.data.preferences

import ua.edmko.data.ThemeProto
import ua.edmko.domain.entities.Theme

internal fun Theme.toProto() = when (this) {
    Theme.DARK -> ThemeProto.DARK
    Theme.LIGHT -> ThemeProto.LIGHT
    else -> ThemeProto.UNRECOGNIZED
}

internal fun ThemeProto.toTheme() = when (this) {
    ThemeProto.DARK -> Theme.DARK
    else -> Theme.LIGHT
}
