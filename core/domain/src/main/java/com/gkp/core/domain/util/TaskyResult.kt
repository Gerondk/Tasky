package com.gkp.core.domain.util

sealed class TaskyResult< out T> {
    data class Success<T> (val data: T) : TaskyResult<T>()
    data class Error(val error: TaskyError) : TaskyResult<Nothing>()
}