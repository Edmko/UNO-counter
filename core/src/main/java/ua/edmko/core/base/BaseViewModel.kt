package ua.edmko.core.base

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean

abstract class BaseViewModel<S : ViewState, E : Event>() : ViewModel() {

    private var initialized = AtomicBoolean(false)

    open fun initialize() {
        if (!initialized.compareAndSet(false, true)) {
            return
        }
    }

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        handleError(throwable)
    }

    protected fun CoroutineScope.smartLaunch(context: suspend () -> Unit): Job {
        return this.launch(errorHandler) { context() }
    }

    private fun handleError(throwable: Throwable) {
        Log.e(throwable.cause.toString(), throwable.message ?: "")
    }

    @org.jetbrains.annotations.VisibleForTesting
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
}
