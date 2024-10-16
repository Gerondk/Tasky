package com.gkp.agenda.domain.notification

interface ReminderNotification {
    fun showNotification(notificationInfo: NotificationInfo)

}

data class NotificationInfo(
    val id: String,
    val title: String,
    val description: String
)

const val DEEP_LINK_URI = "https://gkp.com/tasky/agenda/{agendaItemId}/{agendaItemType}"