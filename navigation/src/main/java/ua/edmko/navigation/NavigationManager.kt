package ua.edmko.navigation

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import javax.inject.Inject

interface NavigationManager {
    fun commands(): Flow<NavigationCommand?>
    fun navigate(directions: NavigationCommand)
    fun back()
}

class NavigationManagerImpl @Inject constructor() : NavigationManager {

    private val _commands: MutableSharedFlow<NavigationCommand?> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    override fun commands(): Flow<NavigationCommand?> = _commands

    override fun navigate(directions: NavigationCommand) {
        _commands.tryEmit(directions)
    }

    override fun back() {
        _commands.tryEmit(NavigationDirections.back)
    }

}