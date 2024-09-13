package ua.edmko.testing.extensions

import kotlinx.coroutines.flow.flowOf
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.kotlin.any
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import ua.edmko.domain.interactor.Interactor
import ua.edmko.domain.interactor.InvokeError
import ua.edmko.domain.interactor.InvokeStarted
import ua.edmko.domain.interactor.InvokeSuccess
import ua.edmko.domain.interactor.SubjectInteractor

inline fun <reified T> Interactor<T>.returnSuccess() = whenever(this.invoke(any(), anyLong())).thenReturn(flowOf(InvokeSuccess))!!

inline fun <reified T> Interactor<T>.returnStarted() = whenever(this.invoke(any(), anyLong())).thenReturn(flowOf(InvokeStarted))!!

inline fun <reified T> Interactor<T>.returnError(error: Exception) =
    whenever(this.invoke(any(), anyLong())).thenReturn(flowOf(InvokeError(error)))!!

inline fun <reified T, reified P : Any> SubjectInteractor<P, T>.returnValue(value: T) =
    whenever(this.createObservable(any())).thenReturn(flowOf(value))!!

inline fun <reified T> Interactor<T>.verifyCall(calls: Int = 1) = verify(this, times(calls)).invoke(any(), anyLong())
