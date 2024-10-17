package com.gkp.agenda.presentation.detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.gkp.agenda.domain.model.AgendaItemType
import com.gkp.agenda.domain.notification.DEEP_LINK_URI
import com.gkp.agenda.presentation.detail.AgendaItemDetailScreen
import com.gkp.agenda.presentation.edit.navigation.navigateToEditAgendaItemGraph
import kotlinx.serialization.Serializable

@Serializable
data class AgendaItemDetailScreenRoute(
    val agendaItemId: String,
    val agendaItemType: AgendaItemType,
)

fun NavController.navigateToAgendaItemDetailScreenRoute(
    agendaItemId: String,
    agendaType: AgendaItemType,
) {
    navigate(AgendaItemDetailScreenRoute(agendaItemId, agendaType))
}

fun NavGraphBuilder.agendaItemDetailScreen(navController: NavController) {
    composable<AgendaItemDetailScreenRoute>(
        deepLinks = listOf(
            navDeepLink <AgendaItemDetailScreenRoute>(
                basePath = DEEP_LINK_URI,
            )
        )
    ) {
        AgendaItemDetailScreen(
            onClickBackButton = navController::navigateUp,
            navigateToTaskEdit = { itemId, agendaItemType ->
                navController.navigateToEditAgendaItemGraph(itemId, agendaItemType)
            }
        )
    }
}

