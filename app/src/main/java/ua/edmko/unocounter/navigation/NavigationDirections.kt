package ua.edmko.unocounter.navigation

import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.NamedNavArgument

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
}