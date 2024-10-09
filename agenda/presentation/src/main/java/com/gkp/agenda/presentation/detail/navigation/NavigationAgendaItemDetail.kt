package com.gkp.agenda.presentation.detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gkp.agenda.presentation.reminder.navigation.navigateToEditReminderGraph
import com.gkp.agenda.presentation.detail.AgendaItemDetailScreen
import com.gkp.agenda.presentation.task.edittask.navigation.navigateToEditTaskGraph
import kotlinx.serialization.Serializable

@Serializable
data class AgendaItemDetailScreenRoute(
    val taskId: String,
    val agendaItemType: AgendaItemType
)

fun NavController.navigateToAgendaItemDetailScreenRoute(
    itemId: String,
    agendaType: AgendaItemType
) {
    navigate(AgendaItemDetailScreenRoute(itemId, agendaType))
}

fun NavGraphBuilder.agendaItemDetailScreen(navController: NavController) {
    composable<AgendaItemDetailScreenRoute> {
        AgendaItemDetailScreen(
            onClickBackButton = navController::navigateUp,
            navigateToTaskEdit = { itemId, agendaItemType ->
                when (agendaItemType) {
                    AgendaItemType.TASK -> navController.navigateToEditTaskGraph(itemId)
                    AgendaItemType.REMINDER -> navController.navigateToEditReminderGraph(itemId)
                }
            }
        )
    }
}

@Serializable
enum class AgendaItemType {
    TASK,
    REMINDER
}