package com.gkp.agenda.presentation

import com.gkp.agenda.presentation.util.DateWithSelected
import com.gkp.agenda.presentation.util.getCurrentAndNextSixWeekDayDates
import com.gkp.agenda.presentation.util.getFullNameInitials
import java.time.LocalDate

data class AgendaUiState(
    val fullName: String = "",
    val selectedDate: LocalDate = LocalDate.now()
) {
    val fullNameInitials: String
        get() =  getFullNameInitials(fullName)

    val uiDates: List<DateWithSelected>
    get() = getCurrentAndNextSixWeekDayDates(selectedDate)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AgendaUiState

        if (fullName != other.fullName) return false
        if (selectedDate != other.selectedDate) return false

        return true
    }

    override fun hashCode(): Int {
        var result = fullName.hashCode()
        result = 31 * result + selectedDate.hashCode()
        return result
    }
}
