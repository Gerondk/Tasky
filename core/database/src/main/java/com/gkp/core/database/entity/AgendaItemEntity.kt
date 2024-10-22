package com.gkp.core.database.entity

import android.annotation.SuppressLint
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gkp.agenda.domain.util.toLocalDateTime
import java.time.LocalDate
import java.time.ZoneId

@Entity
data class AgendaItemEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val title: String,
    val description: String?,
    val time: Long,
    val to: Long? = null,
    val remindAt: Long,
    val isDone: Boolean? = null,
    val photoUrls : String? = null,
    val date: Long = time.toLocalDateTime().toLocalDate().toMillis()
)

@SuppressLint("NewApi")
fun LocalDate.toMillis(): Long {
    return this.atStartOfDay().atZone(ZoneId.of("UTC")).toInstant().toEpochMilli()
}
