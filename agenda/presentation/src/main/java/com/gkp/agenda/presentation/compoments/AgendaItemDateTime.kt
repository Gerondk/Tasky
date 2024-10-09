package com.gkp.agenda.presentation.compoments

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gkp.agenda.presentation.R
import com.gkp.core.designsystem.theme.TaskyBlack
import com.gkp.core.designsystem.theme.TaskyTheme

@Composable
fun AgendaItemDateTime(
    modifier: Modifier = Modifier,
    time: String,
    date: String,
    onClickTime: () -> Unit,
    onClickDate: () -> Unit,
    editMode: Boolean = false,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.at),
            style = MaterialTheme.typography.bodyLarge.copy(
                color = TaskyBlack
            )
        )
        Text(
            modifier = Modifier.clickable {
                onClickTime()
            },
            text = time,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = TaskyBlack
            )
        )
        if (editMode) {
            Icon(
                modifier = Modifier
                    .size(13.dp)
                    .clickable {
                        onClickTime()
                    },
                imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                contentDescription = stringResource(R.string.edit),
                tint = TaskyBlack
            )
        }
        Text(
            modifier = Modifier.clickable {
                onClickDate()
            },
            text = date,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = TaskyBlack
            )
        )
        if (editMode) {
            Icon(
                modifier = Modifier
                    .size(13.dp)
                    .clickable {
                        onClickDate()

                    },
                imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                contentDescription = stringResource(R.string.edit),
                tint = TaskyBlack
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun AgendaItemDateTimePreview() {
    TaskyTheme {
        AgendaItemDateTime(
            time = "12:00",
            date = "Jul 12 2023",
            onClickDate = {},
            onClickTime = {},
            editMode = true
        )
    }

}