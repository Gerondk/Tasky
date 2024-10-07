package com.gkp.agenda.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.gkp.agenda.presentation.AgendaScreen
import com.gkp.agenda.presentation.reminder.navigation.editReminderGraph
import com.gkp.agenda.presentation.reminder.navigation.navigateToEditReminderDescriptionRoute
import com.gkp.agenda.presentation.reminder.navigation.navigateToEditReminderGraph
import com.gkp.agenda.presentation.reminder.navigation.navigateToEditReminderTitleRoute
import com.gkp.agenda.presentation.task.TaskDetailScreen
import com.gkp.agenda.presentation.task.edittask.navigation.editTaskGraph
import kotlinx.serialization.Serializable


@Serializable
data object AgendaGraph

@Serializable
data object AgendaScreenRoute

@Serializable
data object TaskDetailScreenRoute


fun NavController.navigateToAgendaGraph(navOptions: NavOptions? = null) {
    navigate(AgendaGraph, navOptions)
}

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.agendaGraph(
    onLogout: () -> Unit,
    onMenuItemTaskClick: () -> Unit,
    onTaskDescriptionClick: () -> Unit,
    onTaskTitleClick: () -> Unit,
    onEditTaskTitleBackClick: () -> Unit,
    onEditTaskDescriptionBackClick: () -> Unit,
    onClickEditCloseButton: () -> Unit,
    navController: NavController
) {
    navigation<AgendaGraph>(
        startDestination = AgendaScreenRoute
    ) {
        composable<AgendaScreenRoute> {
            AgendaScreen(
                onMenuItemTaskClick = onMenuItemTaskClick,
                onLogout = onLogout,
                onMenuItemReminderClick = {navController.navigateToEditReminderGraph(taskId = 0)}
            )
        }
        composable<TaskDetailScreenRoute> {
            TaskDetailScreen()
        }

        editTaskGraph(
            onTaskTitleClick = onTaskTitleClick,
            onTaskDescriptionClick = onTaskDescriptionClick,
            onEditTaskTitleBackClick = onEditTaskTitleBackClick,
            onEditTaskDescriptionBackClick = onEditTaskDescriptionBackClick,
            onClickEditCloseButton = onClickEditCloseButton,
            navController = navController
        )

        editReminderGraph(
            onBackClick = navController::navigateUp,
            onTitleClick = { navController.navigateToEditReminderTitleRoute()} ,
            onDescriptionClick = { navController.navigateToEditReminderDescriptionRoute()},
            navController = navController
        )
    }
}

