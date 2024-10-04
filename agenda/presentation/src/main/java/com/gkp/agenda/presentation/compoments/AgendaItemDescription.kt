package com.gkp.agenda.presentation.compoments

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gkp.core.designsystem.theme.TaskyBlack
import com.gkp.core.designsystem.theme.TaskyTheme

@Composable
fun AgendaItemDescription(
    modifier: Modifier = Modifier,
    itemDescription: String,
    editMode: Boolean = false
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = itemDescription,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = TaskyBlack,
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(Modifier.weight(1f))
        if (editMode) {
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
private fun AgendaItemTitlePreview() {
    TaskyTheme {
        AgendaItemDescription(
            itemDescription = "Description"
        )
    }
}