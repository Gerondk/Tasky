package com.gkp.agenda.domain.notification

import com.gkp.agenda.domain.model.AgendaItemType

interface ReminderNotification {
    fun showNotification(notificationInfo: NotificationInfo)

}

data class NotificationInfo(
    val id: String,
    val title: String,
    val description: String,
    val agendaItemType: AgendaItemType
)

const val DEEP_LINK_URI = "https://gkp.com/tasky/agenda"