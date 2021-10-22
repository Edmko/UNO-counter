package ua.edmko.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.navArgument

object NavigationDirections {

    val gameSetting = object : NavigationCommand {
        override val argument = emptyList<NamedNavArgument>()
        override val destination: String = "game setting"
        override val builder: NavOptionsBuilder.() -> Unit = {}
    }

    val players = object : NavigationCommand {
        override val argument = emptyList<NamedNavArgument>()
        override val destination: String = "players"
        override val builder: NavOptionsBuilder.() -> Unit = {}
    }

    //PopBack
    val back = object : NavigationCommand {
        override val argument = emptyList<NamedNavArgument>()
        override val destination: String = "back"
        override val builder: NavOptionsBuilder.() -> Unit = {}
    }

    const val GAME_ID = "gameId"
    const val gameDestination = "game/{$GAME_ID}"

    fun game(gameId: String) = object : NavigationCommand {
        override val argument: List<NamedNavArgument> = listOf(
            navArgument(GAME_ID) { defaultValue = "" }
        )
        override val destination: String = "game/$gameId"
        override val builder: NavOptionsBuilder.() -> Unit = {}
    }

    val lobby = object : NavigationCommand {
        override val argument: List<NamedNavArgument> = emptyList()
        override val destination: String = "lobby"
        override val builder: NavOptionsBuilder.() -> Unit = {}
    }
    const val WINNER_NAME = "winner"
    const val gameEndDestination = "gameEnd/{$WINNER_NAME}"

    fun gameEnd(playerName: String) = object : NavigationCommand {
        override val argument: List<NamedNavArgument> = listOf(
            navArgument(WINNER_NAME) { defaultValue = "" }
        )
        override val destination: String = "gameEnd/$playerName"
        override val builder: NavOptionsBuilder.() -> Unit = {
            popUpTo(gameSetting.destination) { inclusive = false }
        }
    }
}