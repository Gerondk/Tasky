package com.gkp.agenda.presentation.compoments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gkp.core.designsystem.theme.TaskyGreen
import com.gkp.core.designsystem.theme.TaskyTextFieldColor
import com.gkp.core.designsystem.theme.TaskyTheme

@Composable
fun AgendaItemHeader(
    modifier: Modifier = Modifier,
    itemName: String,
    leadingBoxColor: Color = TaskyGreen
) {
    Row(
        modifier = modifier
    ) {
        Box(modifier = Modifier
            .size(20.dp)
            .background(leadingBoxColor)
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = itemName,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = TaskyTextFieldColor
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AgendaItemHeaderPreview () {
   TaskyTheme {
       AgendaItemHeader(
           itemName = "Task"
       )
   }
}