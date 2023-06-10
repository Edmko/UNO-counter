package ua.edmko.navigation.commands

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.navArgument
import ua.edmko.core.base.NavigationCommand
import ua.edmko.game.GAME_ID_EXTRA


class GameCommand(gameId: String) : NavigationCommand {

    internal companion object {

        const val DESTINATION = "game/{$GAME_ID_EXTRA}"
    }

    override val argument: List<NamedNavArgument> = listOf(
        navArgument(GAME_ID_EXTRA) { defaultValue = "" },
    )
    override val destination: String = "game/$gameId"
    override val builder: NavOptionsBuilder.() -> Unit = {}
}
