package ua.edmko.navigation.commands

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavOptionsBuilder
import ua.edmko.core.base.NavigationCommand

internal val SettingsCommand = object : NavigationCommand {
    override val argument: List<NamedNavArgument> = emptyList()
    override val destination: String = "settings"
    override val builder: NavOptionsBuilder.() -> Unit = {}
}
