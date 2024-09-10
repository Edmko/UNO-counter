package ua.edmko.domain.interactor

import kotlinx.coroutines.withContext
import ua.edmko.domain.CoroutineDispatchers
import ua.edmko.domain.entities.Theme
import ua.edmko.domain.repository.Preferences
import javax.inject.Inject

class SetTheme @Inject constructor(
    private val preferences: Preferences,
    private val dispatchers: CoroutineDispatchers,
) : Interactor<Theme>() {

    override suspend fun doWork(params: Theme) = withContext(dispatchers.io) {
        preferences.setTheme(params)
    }
}
