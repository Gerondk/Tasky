package com.gkp.agenda.presentation.task.edittask.navigation

import android.annotation.SuppressLint
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.gkp.agenda.presentation.task.edittask.EditTaskDescriptionScreen
import com.gkp.agenda.presentation.task.edittask.EditTaskScreen
import com.gkp.agenda.presentation.EditAgendaItemViewModel
import com.gkp.agenda.presentation.task.edittask.EditTitleScreen
import com.gkp.agenda.presentation.task.edittask.util.sharedViewModel
import kotlinx.serialization.Serializable

@Serializable
data object EditTaskScreenRoute

@Serializable
data class EditTitleScreenRoute(val taskId: Int)

@Serializable
data object EditDescriptionScreenRoute

@Serializable
data class EditTaskGraph(val taskId: Int)

fun NavController.navigateToEditTaskRoute(navOptions: NavOptions? = null) {
    navigate(EditTaskScreenRoute, navOptions)
}

fun NavController.navigateToEditTitleRoute(navOptions: NavOptions? = null) {
    navigate(EditTitleScreenRoute(taskId = 0), navOptions)
}

fun NavController.navigateToEditDescriptionRoute(navOptions: NavOptions? = null) {
    navigate(EditDescriptionScreenRoute, navOptions)
}

fun NavController.navigateToEditTaskGraph(taskId: Int, navOptions: NavOptions? = null) {
    navigate(EditTaskGraph(taskId), navOptions)
}


@SuppressLint("NewApi")
fun NavGraphBuilder.editTaskGraph(
    onTaskTitleClick: () -> Unit,
    onTaskDescriptionClick: () -> Unit,
    onEditTaskTitleBackClick: () -> Unit,
    onEditTaskDescriptionBackClick: () -> Unit,
    onClickEditCloseButton: () -> Unit,
    navController: NavController
) {
    navigation<EditTaskGraph>(
        startDestination = EditTaskScreenRoute
    ) {
        composable<EditTaskScreenRoute> {
            val viewModel = it.sharedViewModel<EditAgendaItemViewModel>(navController)
            EditTaskScreen(
                onClickTaskTitle = onTaskTitleClick,
                onClickTaskDescription = onTaskDescriptionClick,
                onClickEditCloseButton = onClickEditCloseButton,
                viewModel = viewModel
            )
        }
        composable<EditTitleScreenRoute> {
            val viewModel = it.sharedViewModel<EditAgendaItemViewModel>(navController)
            EditTitleScreen(
                onClickBackButton = onEditTaskTitleBackClick,
                viewModel = viewModel
            )
        }
        composable<EditDescriptionScreenRoute> {
            val viewModel = it.sharedViewModel<EditAgendaItemViewModel>(navController)
            EditTaskDescriptionScreen(
                onBackClick = onEditTaskDescriptionBackClick,
                viewModel = viewModel
            )
        }
    }
}