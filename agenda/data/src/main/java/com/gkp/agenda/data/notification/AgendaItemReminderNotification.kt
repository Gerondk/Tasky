package com.gkp.agenda.data.notification

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri
import com.gkp.agenda.data.R
import com.gkp.agenda.domain.notification.DEEP_LINK_URI
import com.gkp.agenda.domain.notification.NotificationInfo
import com.gkp.agenda.domain.notification.ReminderNotification

const val REMINDER_CHANNEL_ID = "REMINDER_CHANNEL"
const val MAIN_ACTIVITY_COMPONENT = "com.gkp.tasky.MainActivity"

@RequiresApi(Build.VERSION_CODES.O)
class AgendaItemReminderNotification(
    private val context: Context,
) : ReminderNotification {

    private val notificationManager = NotificationManagerCompat.from(context)

    init {
        val notificationChannel = NotificationChannel(
            REMINDER_CHANNEL_ID,
            "Reminder",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(
            notificationChannel
        )
    }

    override fun showNotification(notificationInfo: NotificationInfo) {

        val intent = Intent().apply {
            component = ComponentName(
                context.packageName,
                MAIN_ACTIVITY_COMPONENT
            )
            data =
                "$DEEP_LINK_URI/${notificationInfo.id}/${notificationInfo.agendaItemType}".toUri()
            action = Intent.ACTION_VIEW
        }

        val pendingIntent = TaskStackBuilder.create(context).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        }
        val notificationBuilder = NotificationCompat.Builder(context, REMINDER_CHANNEL_ID)
            .setAutoCancel(true)
            .setSmallIcon(R.drawable.tasky_logo)
            .setContentTitle(notificationInfo.title)
            .setContentText(notificationInfo.description)
            .setContentIntent(pendingIntent)
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            return
        }
        notificationManager.notify(
            notificationInfo.id.hashCode(),
            notificationBuilder.build()
        )
    }
}