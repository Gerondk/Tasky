package com.gkp.agenda.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.gkp.agenda.presentation.AgendaScreen
import com.gkp.agenda.presentation.task.TaskDetailScreen
import com.gkp.agenda.presentation.task.edittask.EditTaskViewModel
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
                onLogout = onLogout
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
    }
}

