package ua.edmko.unocounter.navigation

import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.NamedNavArgument

abstract class NavigationCommand {
    abstract val argument : List<NamedNavArgument>
    abstract val destination : String
    abstract val builder: NavOptionsBuilder.() -> Unit
    override fun equals(other: Any?): Boolean {
        return false
    }

}