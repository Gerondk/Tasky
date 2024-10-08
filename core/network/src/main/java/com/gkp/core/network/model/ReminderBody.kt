package com.gkp.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ReminderBody(
    val id: String,
    val title: String,
    val description: String?,
    val time: Long,
    val remindAt: Long
)
