package ua.edmko.navigation.commands

import androidx.navigation.NavOptionsBuilder
import ua.edmko.core.base.NavigationCommand
import ua.edmko.endgame.EndGameRoute

internal class GameEndCommand(playerName: String) : NavigationCommand<EndGameRoute> {

    override val route: EndGameRoute = EndGameRoute(playerName)
    override val builder: NavOptionsBuilder.() -> Unit = { popUpTo(GameSettingCommand.route) { inclusive = false } }
}
