package ua.edmko.unocounter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn
import ua.edmko.domain.entities.Theme
import ua.edmko.domain.interactor.ObserveTheme
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(observeTheme: ObserveTheme) : ViewModel() {

    val theme = observeTheme
        .createObservable(Unit)
        .catch { emit(Theme.DARK) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Theme.DARK,
        )
}
