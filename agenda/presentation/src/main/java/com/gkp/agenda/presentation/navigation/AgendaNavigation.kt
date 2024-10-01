package com.gkp.agenda.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.gkp.agenda.presentation.AgendaScreen
import com.gkp.agenda.presentation.task.TaskDetailScreen
import com.gkp.agenda.presentation.task.edittask.EditDescriptionScreen
import com.gkp.agenda.presentation.task.edittask.EditTaskScreen
import com.gkp.agenda.presentation.task.edittask.EditTitleScreen
import kotlinx.serialization.Serializable


@Serializable
data object AgendaScreenRoute

@Serializable
data object TaskDetailScreenRoute

@Serializable
data object EditTaskScreenRoute

@Serializable
data object EditTitleScreenRoute

@Serializable
data object EditDescriptionScreenRoute


fun NavController.navigateToAgendaRoute(navOptions: NavOptions? = null) {
    navigate(AgendaScreenRoute, navOptions)
}

fun NavController.navigateToEditTaskRoute(navOptions: NavOptions? = null) {
    navigate(EditTaskScreenRoute, navOptions)
}

fun NavController.navigateToEditTitleRoute(navOptions: NavOptions? = null) {
    navigate(EditTitleScreenRoute, navOptions)
}

fun NavController.navigateToEditDescriptionRoute(navOptions: NavOptions? = null) {
    navigate(EditDescriptionScreenRoute, navOptions)
}

fun NavGraphBuilder.agendaScreen(
    onLogout: () -> Unit,
    onMenuItemTaskClick: () -> Unit,
    onTaskDescriptionClick: () -> Unit,
    onTaskTitleClick: () -> Unit,
    onEditTaskTitleBackClick: () -> Unit,
    onEditTaskDescriptionBackClick: () -> Unit,
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
    composable<EditTaskScreenRoute> {
        EditTaskScreen(
            onClickTaskTitle = onTaskTitleClick,
            onClickTaskDescription = onTaskDescriptionClick
        )
    }
    composable<EditTitleScreenRoute> {
        EditTitleScreen(
            onClickBackButton =  onEditTaskTitleBackClick
        )
    }
    composable<EditDescriptionScreenRoute> {
        EditDescriptionScreen(
            onBackClick = onEditTaskDescriptionBackClick
        )
    }
}

