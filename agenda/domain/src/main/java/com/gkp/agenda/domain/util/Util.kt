package com.gkp.agenda.domain.util

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId


fun Long.toLocalDateTime(): LocalDateTime {
    return LocalDateTime.ofInstant(Instant.ofEpochMilli(this), ZoneId.systemDefault())
}

fun Long.formattedDateTime(): String {
    val localDateTime = toLocalDateTime()
    val formattedDate =
        "${localDateTime.month.toString().take(3)} ${localDateTime.dayOfMonth}"
    val formattedTime = String.format("%02d:%02d", localDateTime.hour, localDateTime.minute)
    return "$formattedDate, $formattedTime"
}