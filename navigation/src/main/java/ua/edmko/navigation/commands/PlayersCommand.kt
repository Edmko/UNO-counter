package ua.edmko.navigation.commands

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavOptionsBuilder
import ua.edmko.core.base.NavigationCommand

internal val PlayersCommand = object : NavigationCommand {
    override val argument = emptyList<NamedNavArgument>()
    override val destination: String = "players"
    override val builder: NavOptionsBuilder.() -> Unit = {}
}
