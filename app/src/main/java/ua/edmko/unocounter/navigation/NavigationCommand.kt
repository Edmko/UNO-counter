package ua.edmko.unocounter.navigation

import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.NamedNavArgument

interface NavigationCommand {
    val argument : List<NamedNavArgument>
    val destination : String
    val builder: NavOptionsBuilder.() -> Unit
}