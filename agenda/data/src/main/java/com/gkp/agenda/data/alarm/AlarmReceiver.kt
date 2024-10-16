package com.gkp.agenda.data.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.gkp.agenda.domain.notification.NotificationInfo
import com.gkp.agenda.domain.notification.ReminderNotification
import org.koin.java.KoinJavaComponent.inject

class AlarmReceiver : BroadcastReceiver() {

    private val  reminderNotification by inject<ReminderNotification>(
        ReminderNotification::class.java
    )
    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let {
            with (it) {
                val id = getStringExtra("EXTRA_ID") ?: return
                val title = getStringExtra("EXTRA_TITLE") ?: ""
                val description = getStringExtra("EXTRA_DESCRIPTION")?: ""

               reminderNotification.showNotification(
                   NotificationInfo(
                       id = id ,
                       title = title ,
                       description = description
                   )
               )
            }
        }
    }
}


