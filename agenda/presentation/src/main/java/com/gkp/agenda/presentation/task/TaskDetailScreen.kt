package com.gkp.agenda.presentation.task

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
import com.gkp.agenda.presentation.R
import com.gkp.agenda.presentation.compoments.AgendaBackground
import com.gkp.agenda.presentation.compoments.AgendaItemDateTime
import com.gkp.agenda.presentation.compoments.AgendaItemDescription
import com.gkp.agenda.presentation.compoments.AgendaItemHeader
import com.gkp.agenda.presentation.compoments.AgendaItemReminderTime
import com.gkp.agenda.presentation.compoments.AgendaItemTitle
import com.gkp.core.designsystem.theme.TaskyTextFieldColor
import com.gkp.core.designsystem.theme.TaskyTheme

@Composable
fun TaskDetailScreen(modifier: Modifier = Modifier) {
    TaskDetailScreen(
        onClickBackButton = {},
        onEditClick = {},
        onClickDeleteButton = {}
    )
}

@Composable
private fun TaskDetailScreen(
    onClickBackButton: () -> Unit = {},
    onEditClick: () -> Unit = {},
    onClickDeleteButton: () -> Unit = {},
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
        title = "12 MARCH 2023",
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
                itemName = "Task"
            )
            Spacer(modifier = Modifier.height(42.dp))
            AgendaItemTitle(
                modifier = Modifier.padding(vertical = 16.dp),
            )
            HorizontalDivider()
            AgendaItemDescription(
                modifier = Modifier.padding(vertical = 16.dp),
            )
            HorizontalDivider()
            AgendaItemDateTime(
                modifier = Modifier.padding(vertical = 16.dp),
            )
            HorizontalDivider()
            AgendaItemReminderTime(
                modifier = Modifier.padding(vertical = 16.dp),
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

@Preview
@Composable
private fun TaskDetailScreenPreview() {
    TaskyTheme {
        TaskDetailScreen()
    }

}