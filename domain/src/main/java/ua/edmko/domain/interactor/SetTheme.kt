package ua.edmko.domain.interactor

import ua.edmko.domain.entities.Theme
import ua.edmko.domain.repository.Preferences
import javax.inject.Inject

class SetTheme @Inject constructor(private val preferences: Preferences) : Interactor<Theme>() {

    override suspend fun doWork(params: Theme) {
        preferences.setTheme(params)
    }
}
