package com.gkp.core.domain.util

sealed class TaskyResult< out T> {
    data class Success<T> (val data: T) : TaskyResult<T>()
    data class Error(val error: TaskyError) : TaskyResult<Nothing>()
    fun getDataOrNull(): T? = if (this is Success) data else null
    fun getErrorOrNull(): TaskyError? = if (this is Error) error else null
}

fun <T> TaskyResult<T>.onSuccess(block: (T) -> Unit): TaskyResult<T> {
    if (this is TaskyResult.Success) {
        block(data)
    }
    return this
}

fun <T> TaskyResult<T>.onError(block: (TaskyError) -> Unit): TaskyResult<T> {
    if (this is TaskyResult.Error) {
        block(error)
    }
    return this
}