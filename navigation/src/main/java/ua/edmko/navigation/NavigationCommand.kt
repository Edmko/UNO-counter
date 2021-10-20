package ua.edmko.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavOptionsBuilder

interface NavigationCommand {
    val argument : List<NamedNavArgument>
    val destination : String
    val builder: NavOptionsBuilder.() -> Unit
}