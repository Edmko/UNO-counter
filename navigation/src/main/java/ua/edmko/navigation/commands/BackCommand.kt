package ua.edmko.navigation.commands

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavOptionsBuilder
import ua.edmko.core.base.NavigationCommand

internal val BackCommand = object : NavigationCommand {
    override val argument = emptyList<NamedNavArgument>()
    override val destination: String = "back"
    override val builder: NavOptionsBuilder.() -> Unit = {}
}
