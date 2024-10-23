package com.gkp.agenda.data

import com.gkp.agenda.domain.model.AgendaItem
import com.gkp.agenda.domain.model.AgendaItemType

fun AgendaItem.getType() : AgendaItemType {
    return when(this) {
        is AgendaItem.Event -> AgendaItemType.EVENT
        is AgendaItem.Reminder -> AgendaItemType.REMINDER
        is AgendaItem.Task -> AgendaItemType.TASK
    }
}