package com.gkp.agenda.presentation.home

import com.gkp.agenda.domain.model.AgendaItem
import com.gkp.agenda.presentation.R
import com.gkp.agenda.presentation.util.DateWithSelected
import com.gkp.agenda.presentation.util.getCurrentAndNextSixWeekDayDates
import com.gkp.agenda.presentation.util.getFullNameInitials
import com.gkp.core.ui.UiText
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class AgendaUiState(
    val fullName: String = "",
    val selectedDate: LocalDate = LocalDate.now(),
    val agendaItems: List<AgendaItem> = emptyList(),
) {
    val fullNameInitials: String
        get() = getFullNameInitials(fullName)

    val uiDates: List<DateWithSelected>
        get() = getCurrentAndNextSixWeekDayDates(selectedDate)

    val uiFormattedSelectedDate: UiText
        get() = when (selectedDate) {
            LocalDate.now() -> {
                UiText.StringResource(R.string.today)
            }

            LocalDate.now().minusDays(1) -> {
                UiText.StringResource(R.string.yesterday)
            }

            LocalDate.now().plusDays(1) -> {
                UiText.StringResource(R.string.tomorrow)
            }

            else -> {
                val formatter = DateTimeFormatter.ofPattern("MMM dd")
                selectedDate.format(formatter)
                UiText.DynamicString(selectedDate.format(formatter))
            }
        }

}
