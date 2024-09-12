package com.gkp.core.domain.util

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

inline fun <T> networkApiCall(
    crossinline apiCall: suspend () -> T
): Flow<TaskyResult<T>> = flow {
    emit(TaskyResult.Loading)
    val response = apiCall.invoke()
    emit(TaskyResult.Success(response))
}.catch { cause: Throwable ->
    if (cause is CancellationException) throw  cause
    emit(TaskyResult.Error(errorType = ErrorType.NETWORK_ERROR))
}