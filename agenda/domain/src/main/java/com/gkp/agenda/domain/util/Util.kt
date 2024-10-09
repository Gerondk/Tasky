package com.gkp.agenda.domain.util

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter


fun Long.toLocalDateTime(): LocalDateTime {
    return LocalDateTime.ofInstant(Instant.ofEpochMilli(this), ZoneId.systemDefault())
}

fun Long.formattedDateTime(): String {
    val localDateTime = toLocalDateTime()
    val formatter = DateTimeFormatter.ofPattern("MMM d, HH:mm")
    return formatter.format(localDateTime)
}