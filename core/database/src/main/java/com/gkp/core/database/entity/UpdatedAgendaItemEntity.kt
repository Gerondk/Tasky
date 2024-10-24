package com.gkp.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UpdatedAgendaItemEntity(
    @PrimaryKey
    val id: String,
    val userId: String,
)
