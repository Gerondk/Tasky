package com.gkp.agenda.presentation.compoments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gkp.agenda.presentation.R
import com.gkp.core.designsystem.theme.TaskyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgendaBackground(
    modifier: Modifier = Modifier,
    title: String = "",
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable () -> Unit = {},
    onMenuItemEventClick: () -> Unit = {},
    onMenuItemTaskClick: () -> Unit = {},
    onMenuItemReminderClick: () -> Unit = {},
    hasFloatingActionButton: Boolean = false,
    content: @Composable (PaddingValues) -> Unit,
) {

    Scaffold(
        floatingActionButton = {
            if (hasFloatingActionButton) {
                FabWithAgendaMenu(
                    onMenuItemEventClick = onMenuItemEventClick,
                    onMenuItemTaskClick = onMenuItemTaskClick,
                    onMenuItemReminderClick = onMenuItemReminderClick
                )
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colorScheme.primary
                )
        ) {
            CenterAlignedTopAppBar(
                navigationIcon = navigationIcon,
                title = {
                    Text(text = title)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                actions = { actions() }
            )

            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomEnd)
                    .fillMaxHeight(0.9f)
                    .background(
                        color = MaterialTheme.colorScheme.background, shape = RoundedCornerShape(
                            topStart = 30.dp,
                            topEnd = 30.dp
                        )
                    )
            ) {
                content(padding)
            }

        }
    }


}

@Composable
private fun FabWithAgendaMenu(
    onMenuItemEventClick: () -> Unit = {},
    onMenuItemTaskClick: () -> Unit = {},
    onMenuItemReminderClick: () -> Unit = {},
) {
    var showDropdownMenu by remember {
        mutableStateOf(false)
    }
    Column(horizontalAlignment = Alignment.End) {
        DropdownMenu(
            expanded = showDropdownMenu,
            onDismissRequest = { showDropdownMenu = false }
        ) {
            DropdownMenuItem(
                text = {
                    Text(
                        text = stringResource(R.string.menu_item_event),
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.W400
                        )

                    )
                },
                onClick = {
                    showDropdownMenu = false
                    onMenuItemEventClick()
                }
            )
            HorizontalDivider()
            DropdownMenuItem(
                text = {
                    Text(
                        text = stringResource(R.string.menu_item_task),
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.W400
                        )

                    )
                },
                onClick = {
                    showDropdownMenu = false
                    onMenuItemTaskClick()
                }
            )
            HorizontalDivider()
            DropdownMenuItem(
                text = {
                    Text(
                        text = stringResource(R.string.menu_item_reminder),
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.W400
                        )

                    )
                },
                onClick = {
                    showDropdownMenu = false
                    onMenuItemReminderClick()
                }
            )

        }
        Spacer(Modifier.height(8.dp))
        FloatingActionButton(
            containerColor = MaterialTheme.colorScheme.primary,
            onClick = {
                showDropdownMenu = true
            },
            shape = RoundedCornerShape(10.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(R.string.add_task_event_or_reminder),
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun AgendaBackgroundPreview() {
    TaskyTheme {
        AgendaBackground(
            modifier = Modifier,
            content = {},
            hasFloatingActionButton = true,
            onMenuItemEventClick = {},
            onMenuItemReminderClick = {},
            onMenuItemTaskClick = {}
        )
    }

}