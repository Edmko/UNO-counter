package ua.edmko.testing

import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import ua.edmko.core.base.BaseViewModel
import ua.edmko.core.base.Event
import ua.edmko.core.base.ViewState

abstract class BaseViewModelTest<S : ViewState, E : Event, T : BaseViewModel<S, E>>() {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    lateinit var viewModel: T
        private set

    val viewState: S get() = viewModel.viewStates().value!!

    @Before
    fun initialize() {
        viewModel = createViewModel()
        viewModel.initialize()
    }

    abstract fun createViewModel(): T

    fun test(
        events: List<E>,
        assertions: List<Assertion<S>> = emptyList(),
        verifiers: List<() -> Unit> = emptyList(),
    ) {
        events.forEach {
            viewModel.obtainEvent(it)
        }
        assertions.forEach { (excepted, value) ->
            Assert.assertEquals(excepted, value(viewState))
        }
        verifiers.forEach { it.invoke() }
    }

    fun createAssertion(excepted: Any?, value: (S) -> Any?) = Assertion(excepted, value)
}

data class Assertion<S : ViewState>(
    val excepted: Any?,
    val value: (S) -> Any?,
)
