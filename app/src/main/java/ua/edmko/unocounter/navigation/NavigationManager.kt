package ua.edmko.unocounter.navigation

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.*

class NavigationManager {
    var commands: MutableSharedFlow<NavigationCommand?> = MutableSharedFlow()

    suspend fun navigate(directions: NavigationCommand) {
        commands.emit(directions)
    }
}