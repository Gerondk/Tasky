package com.gkp.agenda.presentation.compoments

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gkp.core.designsystem.theme.TaskyBlack
import com.gkp.core.designsystem.theme.TaskyTextHintColor
import com.gkp.core.designsystem.theme.TaskyTheme

@Composable
fun AgendaItemReminderTime(
    modifier: Modifier = Modifier,
    reminderTime: String = "30 minutes before   ",
    editMode: Boolean = false
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Outlined.Notifications,
            contentDescription = "Reminder",
            tint = TaskyTextHintColor
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = reminderTime,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = TaskyBlack
            )
        )
        Spacer(modifier = Modifier.weight(1f))
        if(editMode) {
            Icon(
                modifier = Modifier.size(13.dp),
                imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                contentDescription = "Edit",
                tint = TaskyBlack
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun AgendaItemReminderTimePreview() {
    TaskyTheme {
        AgendaItemReminderTime()
    }
}