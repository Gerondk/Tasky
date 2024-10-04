package com.gkp.agenda.presentation.task.edittask.navigation

import android.annotation.SuppressLint
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.gkp.agenda.presentation.task.edittask.EditDescriptionScreen
import com.gkp.agenda.presentation.task.edittask.EditTaskScreen
import com.gkp.agenda.presentation.task.edittask.EditTaskViewModel
import com.gkp.agenda.presentation.task.edittask.EditTitleScreen
import kotlinx.serialization.Serializable

@Serializable
data object EditTaskScreenRoute

@Serializable
data class EditTitleScreenRoute(val taskId: Int)

@Serializable
data object EditDescriptionScreenRoute

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


@Serializable
data class EditTaskGraph(val taskId: Int)

@SuppressLint("NewApi")
fun NavGraphBuilder.editTaskGraph(
    onTaskTitleClick: () -> Unit,
    onTaskDescriptionClick: () -> Unit,
    onEditTaskTitleBackClick: () -> Unit,
    onEditTaskDescriptionBackClick: () -> Unit,
    onClickEditCloseButton: () -> Unit
) {
    navigation<EditTaskGraph>(
        startDestination = EditTaskScreenRoute
    ) {
        composable<EditTaskScreenRoute> {
            EditTaskScreen(
                onClickTaskTitle = onTaskTitleClick,
                onClickTaskDescription = onTaskDescriptionClick,
                onClickEditCloseButton = onClickEditCloseButton
            )
        }
        composable<EditTitleScreenRoute> {
            EditTitleScreen(
                onClickBackButton = onEditTaskTitleBackClick
            )
        }
        composable<EditDescriptionScreenRoute> {
            EditDescriptionScreen(
                onBackClick = onEditTaskDescriptionBackClick
            )
        }
    }
}