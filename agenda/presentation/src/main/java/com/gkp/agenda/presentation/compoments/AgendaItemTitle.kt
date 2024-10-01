package com.gkp.agenda.presentation.compoments

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gkp.core.designsystem.theme.TaskyBlack
import com.gkp.core.designsystem.theme.TaskyTheme

@Composable
fun AgendaItemTitle(
    modifier: Modifier = Modifier,
    itemTitle: String = "Task",
    editMode: Boolean = false
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(20.dp)
                .border(
                    1.dp,
                    TaskyBlack,
                    CircleShape
                )
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = itemTitle,
            style = MaterialTheme.typography.titleLarge.copy(
                color = TaskyBlack,
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp
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
        AgendaItemTitle()
    }
}