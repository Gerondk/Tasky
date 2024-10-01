package com.gkp.agenda.presentation.compoments

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gkp.core.designsystem.theme.TaskyBlack
import com.gkp.core.designsystem.theme.TaskyTheme

@Composable
fun AgendaItemDateTime(
    modifier: Modifier = Modifier,
    time: String = "12:00",
    date: String = "Jul 12 2023",
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "At",
            style = MaterialTheme.typography.bodyLarge.copy(
                color = TaskyBlack
            )
        )
        Spacer(Modifier.width(16.dp))
        Text(
            text = time,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = TaskyBlack
            )
        )
        Spacer(Modifier.width(32.dp))
        Text(
            text = date,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = TaskyBlack
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AgendaItemDateTimePreview() {
    TaskyTheme {
        AgendaItemDateTime()
    }

}