package ua.edmko.unocounter.navigation

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow

class NavigationManager {

    var commands = MutableSharedFlow<NavigationCommand>()

    suspend fun navigate(directions: NavigationCommand) {
        commands.emit(directions)
    }
}