package com.gkp.agenda.presentation.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gkp.agenda.domain.model.AgendaItemType
import com.gkp.agenda.presentation.R
import com.gkp.agenda.presentation.compoments.AgendaBackground
import com.gkp.agenda.presentation.compoments.AgendaItemDateTime
import com.gkp.agenda.presentation.compoments.AgendaItemDescription
import com.gkp.agenda.presentation.compoments.AgendaItemHeader
import com.gkp.agenda.presentation.compoments.AgendaItemReminderTime
import com.gkp.agenda.presentation.compoments.AgendaItemTitle
import com.gkp.core.designsystem.theme.TaskyGreen
import com.gkp.core.designsystem.theme.TaskyLightGreen
import com.gkp.core.designsystem.theme.TaskyTextFieldColor
import com.gkp.core.designsystem.theme.TaskyTextHintColor
import com.gkp.core.designsystem.theme.TaskyTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun AgendaItemDetailScreen(
    onClickBackButton: () -> Unit,
    navigateToTaskEdit: (String, AgendaItemType) -> Unit,
) {
    val viewModel = koinViewModel<AgendaItemDetailViewModel>()
    val state = viewModel.uiState

    AgendaItemDetailScreen(
        onClickBackButton = onClickBackButton,
        onEditClick = {
            navigateToTaskEdit(state.id, state.agendaItemType)
        },
        onClickDeleteButton = {},
        state = state
    )
}

@Composable
private fun AgendaItemDetailScreen(
    onClickBackButton: () -> Unit,
    onEditClick: () -> Unit,
    onClickDeleteButton: () -> Unit,
    state: AgendaItemDetailUiState,
) {
    AgendaBackground(
        navigationIcon = {
            IconButton(onClick = onClickBackButton) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(R.string.close_button)
                )
            }
        },
        title = state.uiDate.uppercase(),
        actions = {
            IconButton(onClick = onEditClick) {
                Icon(
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = stringResource(R.string.edit),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            AgendaItemHeader(
                itemName = stringResource(
                    id = when (state.agendaItemType) {
                        AgendaItemType.TASK -> R.string.task
                        AgendaItemType.REMINDER -> R.string.reminder
                        AgendaItemType.EVENT -> R.string.event
                    }
                ),
                leadingBoxColor = when (state.agendaItemType) {
                    AgendaItemType.TASK -> TaskyGreen
                    AgendaItemType.REMINDER -> TaskyTextHintColor
                    AgendaItemType.EVENT -> TaskyLightGreen
                }
            )
            Spacer(modifier = Modifier.height(42.dp))
            AgendaItemTitle(
                modifier = Modifier.padding(vertical = 16.dp),
                itemTitle = state.title
            )
            HorizontalDivider()
            AgendaItemDescription(
                modifier = Modifier.padding(vertical = 16.dp),
                itemDescription = state.description
            )
            HorizontalDivider()
            AgendaItemDateTime(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                onClickDate = {},
                onClickTime = {},
                date = state.uiDate,
                time = state.uiTime,
                dateTimeLabel = stringResource(
                    if (state.agendaItemType == AgendaItemType.EVENT)
                        R.string.from
                    else
                        R.string.at
                )
            )
            HorizontalDivider()
            if (state.agendaItemType == AgendaItemType.EVENT) {
                AgendaItemDateTime(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    date = state.uiToDateTime,
                    time = state.uiToTime,
                    dateTimeLabel = stringResource(R.string.to),
                )
                HorizontalDivider()
            }
            AgendaItemReminderTime(
                modifier = Modifier.padding(vertical = 16.dp),
                reminderTimeText = stringResource(state.reminderTextId),
                onCLickReminderMenuItem = {},
                onDismissReminderMenu = {},
                showReminderMenu = false
            )
            HorizontalDivider()
            Spacer(modifier = Modifier.weight(1f))

            TextButton(
                modifier = Modifier.fillMaxWidth(),
                onClick =  onClickDeleteButton
            ) {
                Text(
                    text = stringResource(
                        when (state.agendaItemType) {
                            AgendaItemType.TASK -> R.string.delete_task
                            AgendaItemType.REMINDER -> R.string.delete_reminder
                            AgendaItemType.EVENT -> R.string.delete_event
                        }
                    ),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = TaskyTextFieldColor
                    )
                )
            }
        }


    }
}

@Preview
@Composable
private fun TaskDetailScreenPreview() {
    TaskyTheme {
        AgendaItemDetailScreen(
            onClickBackButton = {},
            onEditClick = {},
            onClickDeleteButton = {},
            state = AgendaItemDetailUiState(
                title = "Task title",
                description = "Task description"
            )
        )
    }

}