package ua.edmko.navigation

import android.util.Log
import kotlinx.coroutines.flow.*
import javax.inject.Inject

interface NavigationManager {
    val commands: StateFlow<NavigationCommand?>
    fun navigate(directions: NavigationCommand)
    fun back()
}

class NavigationManagerImpl @Inject constructor() : NavigationManager {
    init {
        Log.d("manager","init manager")
    }
    private val _commands: MutableStateFlow<NavigationCommand?> = MutableStateFlow(null)
    override val commands = _commands.asStateFlow()

    override fun navigate(directions: NavigationCommand) {
        _commands.tryEmit(directions)
    }

    override fun back() {
        _commands.tryEmit(NavigationDirections.back)
    }

}