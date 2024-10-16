package com.gkp.agenda.domain.alarm

import com.gkp.agenda.domain.model.AgendaItem

interface AlarmScheduler {
    fun schedule(item: AgendaItem)
    fun cancel(item: AgendaItem)
}