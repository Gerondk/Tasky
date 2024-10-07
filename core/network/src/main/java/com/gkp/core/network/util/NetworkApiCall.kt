package com.gkp.core.network.util

import com.gkp.core.domain.util.DataError
import com.gkp.core.domain.util.TaskyResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.SerializationException
import retrofit2.HttpException
import java.nio.channels.UnresolvedAddressException
import kotlin.coroutines.cancellation.CancellationException

inline fun <T> networkApiCall(
    crossinline apiCall: suspend () -> T,
): Flow<TaskyResult<T>> = flow {
    try {
        emit(TaskyResult.Success(apiCall.invoke()))
    } catch (cause: Throwable) {
        if (cause is CancellationException) throw cause
        emit(TaskyResult.Error(getErrorType(cause)))
    }
}

fun getErrorType(throwable: Throwable) = when (throwable) {
    is UnresolvedAddressException -> DataError.Network.NO_INTERNET
    is SerializationException -> DataError.Network.SERIALIZATION
    is HttpException -> {
        when (throwable.code()) {
            401 -> DataError.Network.UNAUTHORIZED
            408 -> DataError.Network.REQUEST_TIMEOUT
            409 -> DataError.Network.CONFLICT
            413 -> DataError.Network.PAYLOAD_TOO_LARGE
            429 -> DataError.Network.TOO_MANY_REQUESTS
            in 500..599 -> DataError.Network.SERVER_ERROR
            else -> DataError.Network.UNKNOWN
        }
    }

    else -> DataError.Network.UNKNOWN
}