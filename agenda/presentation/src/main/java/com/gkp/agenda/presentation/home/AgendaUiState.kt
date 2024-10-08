package com.gkp.agenda.presentation.home

import com.gkp.agenda.domain.model.AgendaItem
import com.gkp.agenda.presentation.util.DateWithSelected
import com.gkp.agenda.presentation.util.getCurrentAndNextSixWeekDayDates
import com.gkp.agenda.presentation.util.getFullNameInitials
import java.time.LocalDate

data class AgendaUiState(
    val fullName: String = "",
    val selectedDate: LocalDate = LocalDate.now(),
    val agendaItems: List<AgendaItem> = emptyList(),
) {
    val fullNameInitials: String
        get() = getFullNameInitials(fullName)

    val uiDates: List<DateWithSelected>
        get() = getCurrentAndNextSixWeekDayDates(selectedDate)
}
