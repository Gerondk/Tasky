package com.gkp.agenda.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gkp.agenda.domain.model.AgendaItem
import com.gkp.agenda.presentation.home.DropDownMenuParameters
import com.gkp.core.designsystem.theme.TaskyTheme

@Composable
fun AgendaItemsList(
    modifier: Modifier = Modifier,
    agendaItems: List<AgendaItem>,
    onAgendaDropMenuItemClick: (DropDownMenuParameters) -> Unit,
    onTaskTitleClick: (String) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        items(
            items = agendaItems,
            key = {it.id}
        ) {
            AgendaListItem(
                modifier = Modifier.fillMaxWidth(),
                agendaItem = it,
                onAgendaDropMenuItemClick = onAgendaDropMenuItemClick,
                onTaskTitleClick = onTaskTitleClick
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun AgendaItemListPreview() {
    val agendaItems = listOf(
        AgendaItem.Task(
            id = "eros",
            title = "aliquam",
            description = null,
            time = 8296,
            remindAt = 3308,
            isDone = false

        ),
        AgendaItem.Reminder(
            id = "platea",
            title = "dicat",
            description = null,
            time = 2824,
            remindAt = 5690

        ),
        AgendaItem.Task(
            id = "eros",
            title = "aliquam",
            description = null,
            time = 8296,
            remindAt = 3308,
            isDone = false

        ),
        AgendaItem.Reminder(
            id = "platea",
            title = "dicat",
            description = null,
            time = 2824,
            remindAt = 5690

        )
    )
    TaskyTheme {
        AgendaItemsList(
            modifier = Modifier,
            onAgendaDropMenuItemClick = {},
            agendaItems = agendaItems,
            onTaskTitleClick = {}
        )
    }

}