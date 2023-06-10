package ua.edmko.navigation.commands

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavOptionsBuilder
import ua.edmko.core.base.NavigationCommand

internal val LobbyCommand = object : NavigationCommand {
    override val argument: List<NamedNavArgument> = emptyList()
    override val destination: String = "lobby"
    override val builder: NavOptionsBuilder.() -> Unit = {}
}
