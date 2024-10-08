package com.gkp.agenda.domain

import com.gkp.agenda.domain.model.AgendaItem

interface AgendaRepository {
    suspend fun getAgenda(time: Long = System.currentTimeMillis())
    fun addAgendaItem(agendaItem: AgendaItem)
    fun logout()
}