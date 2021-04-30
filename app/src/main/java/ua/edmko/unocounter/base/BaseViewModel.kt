package ua.edmko.unocounter.base

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ua.edmko.unocounter.navigation.NavigationManager
import ua.edmko.unocounter.utils.LIFECYCLE

abstract class BaseViewModel<S : ViewState, A : Action, E : Event>(private val navigationManager: NavigationManager) :
    ViewModel() {
    init {
        Log.d(LIFECYCLE, "Create")
    }

    private val _viewStates: MutableStateFlow<S?> = MutableStateFlow(null)
    fun viewStates(): StateFlow<S?> = _viewStates

    protected var viewState: S
        get() = _viewStates.value
            ?: throw UninitializedPropertyAccessException("\"viewState\" was queried before being initialized")
        set(value) {
            /** StateFlow doesn't work with same values */
            if (_viewStates.value == value) {
                _viewStates.value = null
            }
            _viewStates.value = value
        }

    private val _viewActions: MutableStateFlow<A?> = MutableStateFlow(null)
    fun viewActions(): StateFlow<A?> = _viewActions

    protected var viewAction: A
        get() = _viewActions.value
            ?: throw UninitializedPropertyAccessException("\"viewAction\" was queried before being initialized")
        set(value) {
            /** StateFlow doesn't work with same values */
            if (_viewActions.value == value) {
                _viewActions.value = null
            }

            _viewActions.value = value
        }

    abstract fun obtainEvent(viewEvent: E)

    override fun onCleared() {
        Log.d(LIFECYCLE, "Cleared")
        super.onCleared()
    }
}