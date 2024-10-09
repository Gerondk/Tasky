package com.gkp.agenda.presentation.reminder.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.gkp.agenda.presentation.edit.EditAgendaItemViewModel
import com.gkp.agenda.presentation.reminder.EditReminderScreen
import com.gkp.agenda.presentation.reminder.EditReminderTitleAndDescriptionScreen
import com.gkp.agenda.presentation.task.edittask.util.sharedViewModel
import kotlinx.serialization.Serializable

@Serializable
data class EditReminderGraph(val taskId: String)

@Serializable
data object EditReminderRoute

@Serializable
data class EditReminderTitleAndDescriptionRoute(val editTextFieldName: String)


fun NavController.navigateToEditReminderGraph(taskId: String, navOptions: NavOptions? = null) {
    navigate(EditReminderGraph(taskId), navOptions)
}

fun NavController.navigateToEditReminderRoute(navOptions: NavOptions? = null) {
    navigate(EditReminderRoute, navOptions)
}

fun NavController.navigateToEditReminderTitleAndDescriptionRoute(
    textFieldName: String,
    navOptions: NavOptions? = null,
) {
    navigate(EditReminderTitleAndDescriptionRoute(textFieldName), navOptions)
}

const val EDIT_TEXT_FIELD_NAME_TITLE = "TITLE"
const val EDIT_TEXT_FIELD_NAME_DESCRIPTION = "DESCRIPTION"

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.editReminderGraph(
    onBackClick: () -> Unit,
    navController: NavController,
) {
    navigation<EditReminderGraph>(
        startDestination = EditReminderRoute
    ) {
        composable<EditReminderRoute>() {
            val viewModel = it.sharedViewModel<EditAgendaItemViewModel>(navController)
            EditReminderScreen(
                onClickEditCloseButton = onBackClick,
                onClickTitle = {
                    navController.navigateToEditReminderTitleAndDescriptionRoute(
                        EDIT_TEXT_FIELD_NAME_TITLE
                    )
                },
                onClickDescription = {
                    navController.navigateToEditReminderTitleAndDescriptionRoute(
                        EDIT_TEXT_FIELD_NAME_DESCRIPTION
                    )
                },
                viewModel = viewModel
            )
        }
        composable<EditReminderTitleAndDescriptionRoute>() {
            val viewModel = it.sharedViewModel<EditAgendaItemViewModel>(navController)
            val textFieldName = it.toRoute<EditReminderTitleAndDescriptionRoute>().editTextFieldName
            EditReminderTitleAndDescriptionScreen(
                onClickBackButton = onBackClick,
                textFieldName = textFieldName,
                viewModel = viewModel
            )
        }
    }
}