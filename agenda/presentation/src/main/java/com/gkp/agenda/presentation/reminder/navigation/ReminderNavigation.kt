package com.gkp.agenda.presentation.reminder.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.gkp.agenda.presentation.reminder.EditReminderDescriptionScreen
import com.gkp.agenda.presentation.reminder.EditReminderScreen
import com.gkp.agenda.presentation.reminder.EditReminderTitleScreen
import com.gkp.agenda.presentation.EditAgendaItemViewModel
import com.gkp.agenda.presentation.task.edittask.util.sharedViewModel
import kotlinx.serialization.Serializable

@Serializable
data class EditReminderGraph(val taskId: Int)

@Serializable
data object EditReminderRoute

@Serializable
data object EditReminderTitleRoute

@Serializable
data object EditReminderDescriptionRoute


fun NavController.navigateToEditReminderGraph(taskId: Int, navOptions: NavOptions? = null) {
    navigate(EditReminderGraph(taskId), navOptions)
}

fun NavController.navigateToEditReminderRoute(navOptions: NavOptions? = null) {
    navigate(EditReminderRoute, navOptions)
}

fun NavController.navigateToEditReminderTitleRoute(navOptions: NavOptions? = null) {
    navigate(EditReminderTitleRoute, navOptions)
}

fun NavController.navigateToEditReminderDescriptionRoute(navOptions: NavOptions? = null) {
    navigate(EditReminderDescriptionRoute, navOptions)
}


@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.editReminderGraph(
    onBackClick: () -> Unit,
    onTitleClick: () -> Unit,
    onDescriptionClick: () -> Unit,
    navController: NavController
) {
    navigation<EditReminderGraph>(
        startDestination = EditReminderRoute
    ){
        composable<EditReminderRoute>() {
            val viewModel = it.sharedViewModel<EditAgendaItemViewModel>(navController)
            EditReminderScreen(
                onClickEditCloseButton = onBackClick,
                onClickTitle = onTitleClick,
                onClickDescription = onDescriptionClick,
                viewModel = viewModel
            )
        }
        composable<EditReminderTitleRoute>(){
            val viewModel = it.sharedViewModel<EditAgendaItemViewModel>(navController)
            EditReminderTitleScreen(
                onClickBackButton = onBackClick,
                viewModel = viewModel
            )
        }
        composable<EditReminderDescriptionRoute>(){
            val viewModel = it.sharedViewModel<EditAgendaItemViewModel>(navController)
            EditReminderDescriptionScreen(
                onClickBackButton = onBackClick,
                viewModel = viewModel
            )
        }
    }
}