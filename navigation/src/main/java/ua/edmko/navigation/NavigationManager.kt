package ua.edmko.navigation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

interface NavigationManager {
    val commands: StateFlow<NavigationCommand?>
    fun navigate(directions: NavigationCommand)
    fun back()
}

class NavigationManagerImpl @Inject constructor() : NavigationManager {

    private val _commands: MutableStateFlow<NavigationCommand?> = MutableStateFlow(null)
    override val commands = _commands.asStateFlow()

    override fun navigate(directions: NavigationCommand) {
        _commands.tryEmit(directions)
    }

    override fun back() {
        _commands.tryEmit(NavigationDirections.back)
    }

}