package com.gkp.agenda.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.gkp.agenda.domain.model.AgendaItem
import com.gkp.agenda.presentation.R
import com.gkp.agenda.presentation.home.AgendaScreen
import com.gkp.agenda.presentation.reminder.navigation.editReminderGraph
import com.gkp.agenda.presentation.reminder.navigation.navigateToEditReminderGraph
import com.gkp.agenda.presentation.detail.navigation.AgendaItemType
import com.gkp.agenda.presentation.detail.navigation.navigateToAgendaItemDetailScreenRoute
import com.gkp.agenda.presentation.detail.navigation.agendaItemDetailScreen
import com.gkp.agenda.presentation.task.edittask.navigation.editTaskGraph
import com.gkp.agenda.presentation.task.edittask.navigation.navigateToEditTaskGraph
import kotlinx.serialization.Serializable


@Serializable
data object AgendaGraph

@Serializable
data object AgendaScreenRoute




fun NavController.navigateToAgendaGraph(navOptions: NavOptions? = null) {
    navigate(AgendaGraph, navOptions)
}

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.agendaGraph(
    onLogout: () -> Unit,
    onMenuItemTaskClick: () -> Unit,
    onEditTaskTitleBackClick: () -> Unit,
    onClickEditCloseButton: () -> Unit,
    navController: NavController,
) {
    navigation<AgendaGraph>(
        startDestination = AgendaScreenRoute
    ) {
        composable<AgendaScreenRoute> {
            AgendaScreen(
                onMenuItemTaskClick = onMenuItemTaskClick,
                onLogout = onLogout,
                onMenuItemReminderClick = { navController.navigateToEditReminderGraph(taskId = "") },
                onAgendaDropMenuItemClick = { parameters ->
                    when (parameters.menuItemId) {
                        R.string.open -> {
                            when (parameters.agendaItem) {
                                is AgendaItem.Reminder -> {
                                    navController.navigateToAgendaItemDetailScreenRoute(
                                        itemId = parameters.itemId,
                                        agendaType = AgendaItemType.REMINDER
                                    )
                                }
                                is AgendaItem.Task -> {
                                    navController.navigateToAgendaItemDetailScreenRoute(
                                        itemId = parameters.itemId,
                                        agendaType = AgendaItemType.TASK
                                    )
                                }
                            }
                        }
                        R.string.edit -> {
                            when (parameters.agendaItem) {
                                is AgendaItem.Reminder -> {
                                    navController.navigateToEditReminderGraph(
                                        taskId = parameters.itemId
                                    )
                                }
                                is AgendaItem.Task -> {
                                    navController.navigateToEditTaskGraph(
                                        taskId = parameters.itemId
                                    )
                                }
                            }
                        }
                    }
                }
            )
        }
        agendaItemDetailScreen(navController)
        editTaskGraph(
            onEditTaskTitleBackClick = onEditTaskTitleBackClick,
            onClickEditCloseButton = onClickEditCloseButton,
            navController = navController
        )

        editReminderGraph(
            onBackClick = navController::navigateUp,
            navController = navController
        )
    }
}

