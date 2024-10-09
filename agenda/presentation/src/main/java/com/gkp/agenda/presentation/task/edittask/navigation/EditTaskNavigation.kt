package com.gkp.agenda.presentation.task.edittask.navigation

import android.annotation.SuppressLint
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.gkp.agenda.presentation.edit.EditAgendaItemViewModel
import com.gkp.agenda.presentation.reminder.navigation.EDIT_TEXT_FIELD_NAME_DESCRIPTION
import com.gkp.agenda.presentation.reminder.navigation.EDIT_TEXT_FIELD_NAME_TITLE
import com.gkp.agenda.presentation.task.edittask.EditTaskScreen
import com.gkp.agenda.presentation.task.edittask.EditTaskTitleAndDescriptionScreen
import com.gkp.agenda.presentation.task.edittask.util.sharedViewModel
import kotlinx.serialization.Serializable

@Serializable
data object EditTaskScreenRoute

@Serializable
data class EditTaskTitleAndDescriptionScreenRoute(val editTextFieldName: String)

@Serializable
data class EditTaskGraph(val taskId: Int)

fun NavController.navigateToEditTaskRoute(navOptions: NavOptions? = null) {
    navigate(EditTaskScreenRoute, navOptions)
}

fun NavController.navigateToEditTaskTitleAndDescriptionScreenRoute(
    editTextFieldName: String,
    navOptions: NavOptions? = null,
) {
    navigate(EditTaskTitleAndDescriptionScreenRoute(editTextFieldName), navOptions)
}

fun NavController.navigateToEditTaskGraph(taskId: Int, navOptions: NavOptions? = null) {
    navigate(EditTaskGraph(taskId), navOptions)
}


@SuppressLint("NewApi")
fun NavGraphBuilder.editTaskGraph(
    onEditTaskTitleBackClick: () -> Unit,
    onClickEditCloseButton: () -> Unit,
    navController: NavController,
) {
    navigation<EditTaskGraph>(
        startDestination = EditTaskScreenRoute
    ) {
        composable<EditTaskScreenRoute> {
            val viewModel = it.sharedViewModel<EditAgendaItemViewModel>(navController)
            EditTaskScreen(
                onClickTaskTitle = {
                    navController.navigateToEditTaskTitleAndDescriptionScreenRoute(
                        EDIT_TEXT_FIELD_NAME_TITLE
                    )
                },
                onClickTaskDescription = {
                    navController.navigateToEditTaskTitleAndDescriptionScreenRoute(
                        EDIT_TEXT_FIELD_NAME_DESCRIPTION
                    )
                },
                onClickEditCloseButton = onClickEditCloseButton,
                viewModel = viewModel
            )
        }
        composable<EditTaskTitleAndDescriptionScreenRoute> {
            val viewModel = it.sharedViewModel<EditAgendaItemViewModel>(navController)
            val textFieldName =
                it.toRoute<EditTaskTitleAndDescriptionScreenRoute>().editTextFieldName
            EditTaskTitleAndDescriptionScreen(
                onClickBackButton = onEditTaskTitleBackClick,
                viewModel = viewModel,
                textFieldName = textFieldName
            )
        }
    }
}