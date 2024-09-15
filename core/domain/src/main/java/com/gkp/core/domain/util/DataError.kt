package com.gkp.core.domain.util

sealed interface DataError: TaskyError {
     enum class Network: DataError {
          REQUEST_TIMEOUT,
          UNAUTHORIZED,
          CONFLICT,
          TOO_MANY_REQUESTS,
          NO_INTERNET,
          PAYLOAD_TOO_LARGE,
          SERVER_ERROR,
          SERIALIZATION,
          UNKNOWN
     }

     enum class Local: DataError {
          DISK_FULL
     }
}