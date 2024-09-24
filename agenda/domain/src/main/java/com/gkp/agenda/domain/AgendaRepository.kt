package com.gkp.agenda.domain

import com.gkp.core.domain.util.TaskyResult
import kotlinx.coroutines.flow.Flow

interface AgendaRepository {
    suspend fun getAgenda(time: Long = System.currentTimeMillis())
    fun logout()
}