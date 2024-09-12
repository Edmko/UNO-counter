package ua.edmko.navigation.commands

import androidx.navigation.NavOptionsBuilder
import ua.edmko.core.base.NavigationCommand
import ua.edmko.players.PlayersRoute

internal val PlayersCommand = object : NavigationCommand<PlayersRoute> {

    override val route: PlayersRoute = PlayersRoute
    override val builder: NavOptionsBuilder.() -> Unit = {}
}
