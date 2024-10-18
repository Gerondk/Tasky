package com.gkp.agenda.presentation.home.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.gkp.agenda.domain.model.AgendaItem
import com.gkp.agenda.presentation.R
import com.gkp.agenda.presentation.compoments.applyIf
import com.gkp.agenda.presentation.home.DropDownMenuParameters
import com.gkp.core.designsystem.theme.TaskyGreen
import com.gkp.core.designsystem.theme.TaskyLightGray
import com.gkp.core.designsystem.theme.TaskyLightGreen
import com.gkp.core.designsystem.theme.TaskyTextFieldColor
import com.gkp.core.designsystem.theme.TaskyTheme

@Composable
fun AgendaListItem(
    modifier: Modifier = Modifier,
    onAgendaDropMenuItemClick: (DropDownMenuParameters) -> Unit,
    onTaskTitleClick: (String) -> Unit,
    agendaItem: AgendaItem,
) {
    val titleColor = when(agendaItem){
        is AgendaItem.Task -> MaterialTheme.colorScheme.background
        is AgendaItem.Reminder -> MaterialTheme.colorScheme.primary
        is AgendaItem.Event -> MaterialTheme.colorScheme.primary
    }
    val descriptionAndDateColor = when(agendaItem){
        is AgendaItem.Task -> MaterialTheme.colorScheme.background
        is AgendaItem.Reminder -> TaskyTextFieldColor
        is AgendaItem.Event -> TaskyTextFieldColor
    }
    val backgroundColor = when(agendaItem){
        is AgendaItem.Task -> TaskyGreen
        is AgendaItem.Reminder -> TaskyLightGray
        is AgendaItem.Event -> TaskyLightGreen
    }

    var showAgendaItemDropDownMenu by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(124.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row {
                AgendaItemCircle(
                    modifier = Modifier,
                    borderColor = titleColor,
                    isDone = agendaItem is AgendaItem.Task && agendaItem.isDone
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        modifier = Modifier.applyIf(
                            condition = agendaItem is AgendaItem.Task,
                            block = {
                                clickable {
                                    onTaskTitleClick(agendaItem.id)
                                }
                            }
                        ),
                        text = agendaItem.title,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.W600,
                            color = titleColor
                        ),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        textDecoration = if (agendaItem is AgendaItem.Task && agendaItem.isDone)
                            TextDecoration.LineThrough
                        else
                            null
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = agendaItem.description?:"",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = descriptionAndDateColor,
                            fontWeight = FontWeight.W300
                        ),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }
            }
            Box(modifier = Modifier.align(Alignment.TopEnd)) {
                IconButton(
                    onClick = {
                        showAgendaItemDropDownMenu = true
                    }
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_group),
                        contentDescription = null,
                        tint = titleColor
                    )
                }
                AgendaItemDropDownMenu(
                    modifier = Modifier,
                    expanded = showAgendaItemDropDownMenu,
                    onDismissRequest = {
                        showAgendaItemDropDownMenu = false
                    },
                    onAgendaDropMenuItemClick = {
                        onAgendaDropMenuItemClick(
                            DropDownMenuParameters(
                                agendaItem = agendaItem,
                                menuItemId = it,
                                itemId = agendaItem.id
                            )
                        )
                    }
                )
            }

            Text(
                modifier = Modifier.align(Alignment.BottomEnd),
                text = agendaItem.formattedDateTime,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = descriptionAndDateColor,
                    fontWeight = FontWeight.W300
                )
            )

        }
    }
}

val agendaItemDropDownMenuTextIdList = listOf(
    R.string.open,
    R.string.edit,
    R.string.delete
)
@Composable
fun AgendaItemDropDownMenu(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    onAgendaDropMenuItemClick: (Int) -> Unit
) {
    DropdownMenu(
        modifier = modifier,
        expanded = expanded,
        onDismissRequest = {
            onDismissRequest()
        }
    ){
        agendaItemDropDownMenuTextIdList.forEachIndexed { index, it ->
            DropdownMenuItem(
                text = {
                    Text(
                        text = stringResource(it),
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.W400
                        )
                    )
                },
                onClick = {
                    onAgendaDropMenuItemClick(it)
                    onDismissRequest()
                }
            )
            if (index != agendaItemDropDownMenuTextIdList.lastIndex) {
                HorizontalDivider()
            }
        }

    }
}

@Composable
fun AgendaItemCircle(
    modifier: Modifier = Modifier,
    borderColor: Color,
    isDone: Boolean
) {
    Box(
        modifier = modifier
            .size(16.dp)
            .clip(shape = CircleShape)
            .border(
                width = 2.dp,
                color = borderColor,
                shape = CircleShape
            )
    ) {
        if (isDone) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = stringResource(R.string.task_done),
                tint = MaterialTheme.colorScheme.background
            )
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun AgendaItemCirclePreview() {
    TaskyTheme {
        AgendaItemCircle(
            modifier = Modifier,
            borderColor = MaterialTheme.colorScheme.onBackground,
            isDone = true
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun AgendaListItemPreview(
    @PreviewParameter(PreviewAgendaItemParameter::class) agendaItem: AgendaItem,
) {
    TaskyTheme {
        AgendaListItem(
            modifier = Modifier,
            agendaItem = agendaItem,
            onAgendaDropMenuItemClick = {},
            onTaskTitleClick = {}
        )
    }
}

private class PreviewAgendaItemParameter : PreviewParameterProvider<AgendaItem> {
    override val values: Sequence<AgendaItem>
        get() = sequenceOf(
            AgendaItem.Task(
                id = "hinc",
                title = "Title",
                description = "Description",
                time = 8667,
                remindAt = 3767,
                isDone = false
            ),
            AgendaItem.Reminder(
                id = "hinc",
                title = "Title",
                description = "Description",
                time = 8667,
                remindAt = 3767
            ),
            AgendaItem.Task(
                id = "hinc",
                title = "Title",
                description = "Description",
                time = 8667,
                remindAt = 3767,
                isDone = true
            ),
        )
}


