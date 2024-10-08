package com.gkp.agenda.presentation.task.edittask.util

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.gkp.agenda.presentation.edit.ReminderTimes
import org.koin.androidx.compose.koinViewModel
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

fun getReminderTimeText(reminderTimeMenuIndex: Int): Int {
    return when (reminderTimeMenuIndex) {
        0 -> ReminderTimes.TEN_MINUTES_BEFORE.textId
        1 -> ReminderTimes.THIRTY_MINUTES_BEFORE.textId
        2 -> ReminderTimes.ONE_HOUR_BEFORE.textId
        3 -> ReminderTimes.SIX_HOURS_BEFORE.textId
        4 -> ReminderTimes.ONE_DAY_BEFORE.textId
        else -> 0
    }
}

fun getReminderInDateLong(
    selectedDate: LocalDateTime,
    reminderTimeMenuTextId: Int,
): Long {

    return when (reminderTimeMenuTextId) {
        ReminderTimes.TEN_MINUTES_BEFORE.textId -> {
            val date = selectedDate.minusMinutes(10)
            date.toMillis()
        }

        ReminderTimes.THIRTY_MINUTES_BEFORE.textId -> {
            val date = selectedDate.minusMinutes(30)
            date.toMillis()
        }

        ReminderTimes.ONE_HOUR_BEFORE.textId -> {
            val date = selectedDate.minusHours(1)
            date.toMillis()
        }

        ReminderTimes.SIX_HOURS_BEFORE.textId -> {
            val date = selectedDate.minusHours(6)
            date.toMillis()
        }

        ReminderTimes.ONE_DAY_BEFORE.textId -> {
            val date = selectedDate.minusDays(1)
            date.toMillis()
        }

        else -> 0
    }
}

@SuppressLint("NewApi")
fun LocalDateTime.toMillis(): Long {
    return this.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
}

@SuppressLint("NewApi")
fun Long.toLocalDateTime(): LocalDateTime {
    return LocalDateTime.ofInstant(Instant.ofEpochMilli(this), ZoneId.systemDefault())
}

fun LocalDateTime.toUiTime(): String {
    return String.format("%02d:%02d", this.hour, this.minute)
}

fun LocalDateTime.toUiDate(): String {
    return "${this.month.toString().take(3)} ${this.dayOfMonth} ${this.year}"
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return koinViewModel(viewModelStoreOwner = parentEntry)
}
