package ua.edmko.core.base

import androidx.navigation.NavOptionsBuilder

interface NavigationCommand<T : Any> {
    val route: T
    val builder: NavOptionsBuilder.() -> Unit
}
