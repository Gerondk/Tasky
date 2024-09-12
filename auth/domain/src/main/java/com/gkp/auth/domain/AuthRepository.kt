package com.gkp.auth.domain

import com.gkp.core.domain.util.TaskyResult
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun login (email: String, password: String) : Flow<TaskyResult<Unit>>
    fun register(fullName: String, email: String, password: String) : Flow<TaskyResult<Unit>>
}