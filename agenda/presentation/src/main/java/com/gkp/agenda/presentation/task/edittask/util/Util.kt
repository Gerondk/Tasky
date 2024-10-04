package com.gkp.agenda.presentation.task.edittask.util

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.gkp.agenda.presentation.task.edittask.ReminderTimes
import java.time.LocalDateTime

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

fun LocalDateTime.toUiTime(): String {
    return "${this.hour}:${this.minute}"
}

fun LocalDateTime.toUiDate(): String {
    return "${this.month.toString().take(3)} ${this.dayOfMonth} ${this.year}"
}

@Composable
inline fun <reified T: ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController) : T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}