package ua.edmko.core.base

import kotlinx.coroutines.flow.Flow

interface NavigationManager {

    fun commands(): Flow<NavigationCommand>

    fun navigate(directions: NavigationCommand)

    fun back()
}
