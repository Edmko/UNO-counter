package ua.edmko.unocounter.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ua.edmko.unocounter.navigation.NavigationCommand
import ua.edmko.unocounter.navigation.NavigationDirections
import ua.edmko.unocounter.navigation.NavigationManager
import ua.edmko.unocounter.utils.LIFECYCLE

abstract class BaseViewModel<S : ViewState, E : Event>(private val navigationManager: NavigationManager) :
    ViewModel() {

    init {
        Log.d(LIFECYCLE, "Create ${this.javaClass.simpleName}")
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

    abstract fun obtainEvent(viewEvent: E)

    protected suspend fun navigateTo(destination: NavigationCommand) {
        navigationManager.navigate(destination)
    }

    override fun onCleared() {
        Log.d(LIFECYCLE, "Cleared ${this.javaClass.simpleName}")
        super.onCleared()
    }
}