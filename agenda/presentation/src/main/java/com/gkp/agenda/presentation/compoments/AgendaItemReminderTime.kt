package com.gkp.agenda.presentation.compoments

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gkp.agenda.presentation.R
import com.gkp.core.designsystem.theme.TaskyBlack
import com.gkp.core.designsystem.theme.TaskyTextHintColor
import com.gkp.core.designsystem.theme.TaskyTheme

@Composable
fun AgendaItemReminderTime(
    modifier: Modifier = Modifier,
    reminderTimeText: String,
    editMode: Boolean = false,
    onCLickReminderMenuItem: (Int) -> Unit,
    onDismissReminderMenu: () -> Unit,
    showReminderMenu: Boolean = false,
) {
    Box(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = stringResource(R.string.reminder),
                tint = TaskyTextHintColor
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = reminderTimeText,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = TaskyBlack
                )
            )
            Spacer(modifier = Modifier.weight(1f))
            if (editMode) {
                Icon(
                    modifier = Modifier.size(13.dp),
                    imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                    contentDescription = stringResource(R.string.edit),
                    tint = TaskyBlack
                )
            }
        }

        ReminderDropDownMenu(
            modifier = Modifier.align(Alignment.CenterEnd),
            expanded = showReminderMenu,
            onDismissRequest = { onDismissReminderMenu() },
            onCLickReminderMenuItem = {
                onCLickReminderMenuItem(it)
                onDismissReminderMenu()
            }
        )
    }
}

@Composable
private fun ReminderDropDownMenu(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    onCLickReminderMenuItem: (Int) -> Unit,
) {
    DropdownMenu(
        modifier = modifier,
        expanded = expanded,
        onDismissRequest = { onDismissRequest() }
    ) {
        reminderTimeList.forEachIndexed { index, reminderTimeTextResId ->
            DropdownMenuItem(
                text = {
                    Text(
                        text = stringResource(reminderTimeTextResId),
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.W400
                        )

                    )
                },
                onClick = {
                    onCLickReminderMenuItem(index)
                    onDismissRequest()
                }
            )
        }

    }
}

val reminderTimeList = listOf(
    R.string.reminder_10_minutes_before,
    R.string.reminder_30_minutes_before,
    R.string.reminder_1_hour_before,
    R.string.reminder_6_hour_before,
    R.string.reminder_1_day_before
)

@Preview(showBackground = true)
@Composable
private fun AgendaItemReminderTimePreview() {
    TaskyTheme {
        AgendaItemReminderTime(
            reminderTimeText = "10 minutes before",
            onCLickReminderMenuItem = {},
            onDismissReminderMenu = {}
        )
    }
}