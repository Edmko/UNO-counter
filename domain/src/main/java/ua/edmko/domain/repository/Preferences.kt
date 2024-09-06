package ua.edmko.domain.repository

import kotlinx.coroutines.flow.Flow
import ua.edmko.domain.entities.Theme

interface Preferences {

    val theme: Flow<Theme>

    suspend fun setTheme(theme: Theme)
}
