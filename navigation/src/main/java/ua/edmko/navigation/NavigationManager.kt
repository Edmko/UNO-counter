package ua.edmko.navigation

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class NavigationManager {
    private val _commands: MutableSharedFlow<NavigationCommand?> = MutableSharedFlow()
    val commands = _commands.asSharedFlow()

    fun navigate(directions: NavigationCommand) {
        _commands.tryEmit(directions)
    }

   fun back() {
        _commands.tryEmit(NavigationDirections.back)
    }

}