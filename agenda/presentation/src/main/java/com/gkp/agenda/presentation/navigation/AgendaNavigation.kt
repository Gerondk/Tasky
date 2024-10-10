package com.gkp.agenda.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.gkp.agenda.domain.model.AgendaItem
import com.gkp.agenda.presentation.R
import com.gkp.agenda.presentation.detail.navigation.AgendaItemType
import com.gkp.agenda.presentation.detail.navigation.agendaItemDetailScreen
import com.gkp.agenda.presentation.detail.navigation.navigateToAgendaItemDetailScreenRoute
import com.gkp.agenda.presentation.home.AgendaScreen
import com.gkp.agenda.presentation.edit.navigation.editAgendaItemGraph
import com.gkp.agenda.presentation.edit.navigation.navigateToEditAgendaItemGraph
import kotlinx.serialization.Serializable


@Serializable
data object AgendaGraph

@Serializable
data object AgendaScreenRoute


fun NavController.navigateToAgendaGraph(navOptions: NavOptions? = null) {
    navigate(AgendaGraph, navOptions)
}

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.agendaGraph(
    onLogout: () -> Unit,
    navController: NavController,
) {
    navigation<AgendaGraph>(
        startDestination = AgendaScreenRoute
    ) {
        composable<AgendaScreenRoute> {
            AgendaScreen(
                onMenuItemTaskClick = {
                    navController.navigateToEditAgendaItemGraph(
                        agendaItemType = AgendaItemType.TASK
                    )
                },
                onLogout = onLogout,
                onMenuItemReminderClick = {
                    navController.navigateToEditAgendaItemGraph(
                        agendaItemType = AgendaItemType.REMINDER
                    )
                },
                onAgendaDropMenuItemClick = { parameters ->
                    when (parameters.menuItemId) {
                        R.string.open -> {
                            when (parameters.agendaItem) {
                                is AgendaItem.Reminder -> {
                                    navController.navigateToAgendaItemDetailScreenRoute(
                                        agendaItemId = parameters.itemId,
                                        agendaType = AgendaItemType.REMINDER
                                    )
                                }

                                is AgendaItem.Task -> {
                                    navController.navigateToAgendaItemDetailScreenRoute(
                                        agendaItemId = parameters.itemId,
                                        agendaType = AgendaItemType.TASK
                                    )
                                }
                            }
                        }

                        R.string.edit -> {
                            when (parameters.agendaItem) {
                                is AgendaItem.Reminder -> {
                                    navController.navigateToEditAgendaItemGraph(
                                        agendaItemId = parameters.itemId,
                                        agendaItemType = AgendaItemType.REMINDER
                                    )
                                }

                                is AgendaItem.Task -> {
                                    navController.navigateToEditAgendaItemGraph(
                                        agendaItemId = parameters.itemId,
                                        agendaItemType = AgendaItemType.TASK
                                    )
                                }
                            }
                        }
                    }
                }
            )
        }
        agendaItemDetailScreen(navController)
        editAgendaItemGraph(
            onEditAgendaItemTitleBackClick = navController::navigateUp,
            onClickEditCloseButton = navController::navigateUp,
            navController = navController
        )

//        editReminderGraph(
//            onBackClick = navController::navigateUp,
//            navController = navController
//        )
    }
}

