package ua.edmko.navigation.commands

import androidx.navigation.NavOptionsBuilder
import ua.edmko.core.base.NavigationCommand
import ua.edmko.game.GameScreenRoute

class GameCommand(
    gameId: String,
) : NavigationCommand<GameScreenRoute> {

    override val route = GameScreenRoute(gameId)
    override val builder: NavOptionsBuilder.() -> Unit = {}
}
