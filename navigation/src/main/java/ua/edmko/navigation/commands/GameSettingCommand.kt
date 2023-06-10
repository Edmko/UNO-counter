package ua.edmko.navigation.commands

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavOptionsBuilder
import ua.edmko.core.base.NavigationCommand

internal val GameSettingCommand = object : NavigationCommand {
    override val argument = emptyList<NamedNavArgument>()
    override val destination: String = "game setting"
    override val builder: NavOptionsBuilder.() -> Unit = {}
}
