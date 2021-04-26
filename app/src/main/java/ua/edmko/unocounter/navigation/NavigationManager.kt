package ua.edmko.unocounter.navigation

import kotlinx.coroutines.flow.MutableStateFlow

class NavigationManager {
    var commands = MutableStateFlow(NavigationDirections.default)

    fun navigate(directions: NavigationCommand){ commands.value = directions }
}