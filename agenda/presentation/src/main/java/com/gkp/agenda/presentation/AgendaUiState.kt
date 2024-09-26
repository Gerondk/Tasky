package com.gkp.agenda.presentation

import com.gkp.agenda.presentation.util.DateWithSelected
import com.gkp.agenda.presentation.util.getCurrentAndNextSixWeekDayDates
import com.gkp.agenda.presentation.util.getFullNameInitials
import java.time.LocalDate

data class AgendaUiState(
    val fullName: String = "",
    val selectedDate: LocalDate = LocalDate.now(),
) {
    val fullNameInitials: String
        get() = getFullNameInitials(fullName)

    val uiDates: List<DateWithSelected>
        get() = getCurrentAndNextSixWeekDayDates(selectedDate)
}
