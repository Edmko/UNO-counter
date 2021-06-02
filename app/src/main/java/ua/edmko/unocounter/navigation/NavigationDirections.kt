package ua.edmko.unocounter.navigation

import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.compose.navArgument

object NavigationDirections {

    val splash = object : NavigationCommand() {
        override val argument = emptyList<NamedNavArgument>()
        override val destination: String = "splash"
        override val builder: NavOptionsBuilder.() -> Unit  = {}
    }

    val gameSetting = object : NavigationCommand() {
        override val argument = emptyList<NamedNavArgument>()
        override val destination: String = "game setting"
        override val builder: NavOptionsBuilder.() -> Unit  = {
            popUpTo(splash.destination){ inclusive = true}
        }
    }

    val players = object : NavigationCommand(){
        override val argument = emptyList<NamedNavArgument>()
        override val destination: String = "players"
        override val builder: NavOptionsBuilder.() -> Unit  = {}
    }

    val back = object : NavigationCommand(){
        override val argument = emptyList<NamedNavArgument>()
        override val destination: String = "back"
        override val builder: NavOptionsBuilder.() -> Unit  = {}
    }

    const val GAME_ID = "gameId"
    const val gameDestination = "game/{$GAME_ID}"

    fun game(gameId: String) = object : NavigationCommand(){
        override val argument: List<NamedNavArgument> = listOf(
            navArgument("gameId") { defaultValue = "" }
        )
        override val destination: String = "game/$gameId"
        override val builder: NavOptionsBuilder.() -> Unit  = {}
    }
}