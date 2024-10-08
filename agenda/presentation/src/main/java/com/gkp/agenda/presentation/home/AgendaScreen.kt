package com.gkp.agenda.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gkp.agenda.presentation.R
import com.gkp.agenda.presentation.compoments.AgendaBackground
import com.gkp.agenda.presentation.compoments.DayItem
import com.gkp.agenda.presentation.compoments.TaskyCalendarDay
import com.gkp.core.designsystem.theme.TaskyTextHintColor
import com.gkp.core.designsystem.theme.TaskyTheme
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate

@Composable
fun AgendaScreen(
    onLogout: () -> Unit,
    onMenuItemTaskClick: () -> Unit,
    onMenuItemReminderClick: () -> Unit,
) {
    val viewModel = koinViewModel<AgendaViewModel>()
    val agendaUiState = viewModel.agendaUiState

    AgendaScreen(
        modifier = Modifier,
        onMenuItemTaskClick = onMenuItemTaskClick,
        onMenuItemReminderClick = onMenuItemReminderClick,
        onLogout = {
            onLogout()
            viewModel.logout()
        },
        onDateSelected = viewModel::onDateSelected,
        agendaUiState = agendaUiState
    )

}

@Composable
internal fun AgendaScreen(
    modifier: Modifier = Modifier,
    onLogout: () -> Unit,
    onDateSelected: (LocalDate) -> Unit = {},
    onMenuItemTaskClick: () -> Unit,
    onMenuItemReminderClick: () -> Unit,
    agendaUiState: AgendaUiState,
) {

    var showDropdownMenu by remember {
        mutableStateOf(false)
    }

    AgendaBackground(
        modifier = modifier,
        onMenuItemTaskClick = onMenuItemTaskClick,
        onMenuItemReminderClick = onMenuItemReminderClick,
        hasFloatingActionButton = true,
        actions = {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(
                        color = MaterialTheme.colorScheme.background
                    )
                    .clickable { showDropdownMenu = true },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = agendaUiState.fullNameInitials,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = TaskyTextHintColor,
                        fontSize = 13.sp
                    )
                )
            }
            DropdownMenu(
                expanded = showDropdownMenu,
                onDismissRequest = {
                    showDropdownMenu = false
                }
            ) {
                DropdownMenuItem(
                    text = {
                        Text(text = "Logout")
                    },
                    onClick = {
                        showDropdownMenu = false
                        onLogout()
                    },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_logout),
                            contentDescription = stringResource(R.string.logout)
                        )
                    }
                )
            }
        },
        navigationIcon = {
            Text(
                text = agendaUiState.selectedDate.month.name,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    ) { padding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .clip(
                    shape = RoundedCornerShape(
                        topStart = 30.dp,
                        topEnd = 30.dp
                    )
                ),

            ) {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,

                ) {
                items(agendaUiState.uiDates) {
                    DayItem(
                        modifier = Modifier,
                        day = TaskyCalendarDay(
                            weekDay = it.date.dayOfWeek.name.take(2),
                            monthDay = it.date.dayOfMonth.toString()
                        ),
                        onClick = { onDateSelected(it.date) },
                        isDateSelected = it.isSelected
                    )
                }
            }
        }
    }

}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun AgendaScreenPreview() {
    TaskyTheme {
        AgendaScreen(
            modifier = Modifier,
            onLogout = {},
            agendaUiState = AgendaUiState(
                fullName = "Ksdda Mbugua"
            ),
            onMenuItemTaskClick = {},
            onMenuItemReminderClick = {}
        )
    }
}
