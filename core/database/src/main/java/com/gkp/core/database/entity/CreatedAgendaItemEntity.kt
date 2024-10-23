package com.gkp.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CreatedAgendaItemEntity(
    @PrimaryKey
    val id: String,
    val  userId: String,
)
