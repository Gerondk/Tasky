package com.gkp.agenda.domain

import com.gkp.agenda.domain.model.Task
import com.gkp.core.domain.util.TaskyResult
import kotlinx.coroutines.flow.Flow

interface AgendaRepository {
    suspend fun getAgenda(time: Long = System.currentTimeMillis())
    fun addTask(task: Task)
    fun logout()
}