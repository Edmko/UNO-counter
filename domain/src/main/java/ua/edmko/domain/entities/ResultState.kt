package ua.edmko.domain.entities

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed interface ResultState<out T> {

    data class Success<T>(val data: T) : ResultState<T>

    data object Loading : ResultState<Nothing>

    data class Error(val error: Throwable? = null) : ResultState<Nothing>
}

fun <T> Flow<T>.asResult(): Flow<ResultState<T>> {
    return this
        .map<T, ResultState<T>> { ResultState.Success(it) }
        .onStart { emit(ResultState.Loading) }
        .catch { emit(ResultState.Error(it)) }
}
