package ua.edmko.navigation.commands

import androidx.navigation.NavOptionsBuilder
import androidx.navigation.navArgument
import ua.edmko.core.base.NavigationCommand

internal class GameEndCommand(playerName: String) : NavigationCommand {

    internal companion object {

        const val WINNER_NAME_EXTRA = "winner"
        const val DESTINATION = "gameEnd/{$WINNER_NAME_EXTRA}"
    }

    override val argument = listOf(navArgument(WINNER_NAME_EXTRA) { defaultValue = "" })
    override val destination: String = "gameEnd/$playerName"
    override val builder: NavOptionsBuilder.() -> Unit = { popUpTo(GameSettingCommand.destination) { inclusive = false } }
}
