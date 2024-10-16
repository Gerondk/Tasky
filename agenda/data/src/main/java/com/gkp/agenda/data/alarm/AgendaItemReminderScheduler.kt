package com.gkp.agenda.data.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.gkp.agenda.domain.alarm.AlarmScheduler
import com.gkp.agenda.domain.model.AgendaItem

const val EXTRA_TITLE = "EXTRA_TITLE"
const val EXTRA_DESCRIPTION = "EXTRA_DESCRIPTION"
const val EXTRA_ID = "EXTRA_ID"

class AgendaItemReminderScheduler(
    private val context: Context,
) : AlarmScheduler {
    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    override fun schedule(item: AgendaItem) {
        val pendingIntent = createPendingIntent(item)
        alarmManager.setAndAllowWhileIdle(
             AlarmManager.RTC_WAKEUP,
            item.remindAt,
            pendingIntent
        )
    }

    override fun cancel(item: AgendaItem) {
        val pendingIntent = createPendingIntent(item)
        alarmManager.cancel(pendingIntent)
    }

    private fun createPendingIntent(item: AgendaItem): PendingIntent {
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra(EXTRA_ID, item.id)
            putExtra(EXTRA_TITLE, item.title)
            putExtra(EXTRA_DESCRIPTION, item.description)
        }
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            item.id.hashCode(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        return pendingIntent
    }
}