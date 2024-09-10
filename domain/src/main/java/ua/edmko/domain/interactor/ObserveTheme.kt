package ua.edmko.domain.interactor

import kotlinx.coroutines.flow.Flow
import ua.edmko.domain.entities.Theme
import ua.edmko.domain.repository.Preferences
import javax.inject.Inject

class ObserveTheme @Inject constructor(
    private val preferences: Preferences,
) : SubjectInteractor<Unit, Theme>() {

    override fun createObservable(params: Unit): Flow<Theme> {
        return preferences.theme
    }
}
