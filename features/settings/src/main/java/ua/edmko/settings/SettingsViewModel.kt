package ua.edmko.settings

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ua.edmko.core.base.BaseViewModel
import ua.edmko.domain.entities.Theme
import ua.edmko.domain.interactor.ObserveTheme
import ua.edmko.domain.interactor.SetTheme
import javax.inject.Inject

@HiltViewModel
internal class SettingsViewModel @Inject constructor(
    private val navigator: SettingsNavigator,
    private val setTheme: SetTheme,
    private val observeTheme: ObserveTheme,
) : BaseViewModel<SettingsViewState, SettingsEvent>() {

    override fun initialize() {
        super.initialize()
        viewState = SettingsViewState()
        viewModelScope.launch {
            observeTheme.createObservable(Unit).collect {
                viewState = viewState.copy(theme = it)
            }
        }
    }

    override fun obtainEvent(viewEvent: SettingsEvent) {
        when (viewEvent) {
            PrivacyClick -> navigator.toPolicy()
            is SetThemeEvent -> updateTheme(viewEvent.theme)
            ThemeClick -> viewState = viewState.copy(dialog = Dialog.Theme)
            NavigateBack -> navigator.back()
        }
    }

    private fun updateTheme(theme: Theme) = viewModelScope.launch {
        viewState = viewState.copy(dialog = null)
        setTheme(theme).collect()
    }
}
