package ua.edmko.navigation.commands

import androidx.navigation.NavOptionsBuilder
import ua.edmko.core.base.NavigationCommand
import ua.edmko.settings.SettingsRoute

internal val SettingsCommand = object : NavigationCommand<SettingsRoute> {
    override val route: SettingsRoute = SettingsRoute
    override val builder: NavOptionsBuilder.() -> Unit = {}
}
