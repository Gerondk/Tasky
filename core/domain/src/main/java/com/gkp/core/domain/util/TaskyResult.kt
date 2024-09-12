package com.gkp.core.domain.util

sealed class TaskyResult< out T> {
    data object Loading : TaskyResult<Nothing>()
    data class Success<T> (val data: T) : TaskyResult<T>()
    data class Error(val errorType: ErrorType) : TaskyResult<Nothing>()

//    fun getData() = (this as? Success)?.data
//    fun getError() = (this as? Error)?.errorType
}