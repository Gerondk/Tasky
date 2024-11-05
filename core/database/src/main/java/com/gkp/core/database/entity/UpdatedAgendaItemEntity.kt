package com.gkp.core.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UpdatedAgendaItemEntity(
    @PrimaryKey
    val agendaItemId: String,
    val userId: String,
    @Embedded
    val agendaItem: AgendaItemEntity
)
