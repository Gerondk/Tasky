package com.gkp.agenda.presentation.util

import android.annotation.SuppressLint
import java.time.LocalDate
import java.time.ZoneId

private const val CURRENT_AND_NEXT_SIX_DATES = 7

fun getFullNameInitials(fullName: String): String {
    val names = fullName
        .trim()
        .split(" ")
        .filterNot {
            it.isBlank() || it.isEmpty()
        }

    val initialsBuilder = StringBuilder()
    if (names.size == 1) {
        initialsBuilder.append(names[0].take(2).uppercase())
        return initialsBuilder.toString()
    }
    if (names.size == 2) {
        initialsBuilder.append("${names[0].first()}${names[1].first()}".uppercase())
        return initialsBuilder.toString()
    }
    if (names.size >= 3) {
        initialsBuilder.append("${names[0].first()}${names.last().first()}".uppercase())
        return initialsBuilder.toString()
    }
    return ""
}

@SuppressLint("NewApi")
fun LocalDate.toMillis(): Long {
    return this.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
}

fun getCurrentAndNextSixWeekDayDates(
    selectedDate: LocalDate,
): List<DateWithSelected> {
    val today = LocalDate.now()

    return buildList {
        repeat(CURRENT_AND_NEXT_SIX_DATES) {
            val date = today.plusDays(it.toLong())
            add(
                DateWithSelected(
                    date = date,
                    isSelected = date == selectedDate
                )
            )
        }
    }
}

data class DateWithSelected(
    val date: LocalDate,
    val isSelected: Boolean,
)
