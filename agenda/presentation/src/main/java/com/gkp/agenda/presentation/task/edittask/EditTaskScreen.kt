package com.gkp.agenda.presentation.task.edittask

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
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
import com.gkp.agenda.presentation.R
import com.gkp.agenda.presentation.compoments.AgendaBackground
import com.gkp.agenda.presentation.compoments.AgendaItemDateTime
import com.gkp.agenda.presentation.compoments.AgendaItemDescription
import com.gkp.agenda.presentation.compoments.AgendaItemHeader
import com.gkp.agenda.presentation.compoments.AgendaItemReminderTime
import com.gkp.agenda.presentation.compoments.AgendaItemTitle
import com.gkp.core.designsystem.theme.TaskyTextFieldColor
import com.gkp.core.designsystem.theme.TaskyTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun EditTaskScreen(
    modifier: Modifier = Modifier,
    onClickTaskTitle: () -> Unit = {},
    onClickTaskDescription: () -> Unit = {},
    onClickEditButton: () -> Unit = {},
    onClickDeleteButton: () -> Unit = {},
    onClickCloseButton: () -> Unit = {}
) {
    val viewModel = koinViewModel<EditTaskViewModel>()
    EditTaskScreen(
        onClickTaskTitle = onClickTaskTitle,
        onClickTaskDescription = onClickTaskDescription,
        onClickEditButton = {},
        onClickDeleteButton = {},
        onClickCloseButton = {}
    )
}

@Composable
private fun EditTaskScreen(
    onClickTaskTitle: () -> Unit = {},
    onClickTaskDescription: () -> Unit = {},
    onClickCloseButton: () -> Unit = {},
    onClickEditButton: () -> Unit = {},
    onClickDeleteButton: () -> Unit = {}
) {
    AgendaBackground(
        title = stringResource(R.string.edit_task),
        navigationIcon = {
            IconButton(onClick = { onClickCloseButton() }) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(R.string.close_button)
                )
            }
        },
        actions = {
            IconButton(onClick = { onClickEditButton() }) {
                Text(
                    text = stringResource(R.string.save),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = MaterialTheme.colorScheme.onPrimary
                    )
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
                itemName = "Task"
            )
            Spacer(modifier = Modifier.height(42.dp))
            AgendaItemTitle(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .clickable { onClickTaskTitle() },
                editMode = true
            )
            HorizontalDivider()
            AgendaItemDescription(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .clickable { onClickTaskDescription() },
                editMode = true
            )
            HorizontalDivider()
            AgendaItemDateTime(
                modifier = Modifier.padding(vertical = 16.dp),
            )
            HorizontalDivider()
            AgendaItemReminderTime(
                modifier = Modifier.padding(vertical = 16.dp),
                editMode = true
            )
            HorizontalDivider()
            Spacer(modifier = Modifier.weight(1f))

            TextButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onClickDeleteButton() }
            ) {
                Text(
                    text = stringResource(R.string.delete_task),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = TaskyTextFieldColor
                    )
                )
            }
        }

    }

}


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun TaskScreenPreview() {
    TaskyTheme {
        EditTaskScreen(
            onClickCloseButton = {}
        )
    }

}