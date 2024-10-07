package com.gkp.tasky.nagivation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.gkp.agenda.presentation.navigation.AgendaGraph
import com.gkp.agenda.presentation.navigation.agendaGraph
import com.gkp.agenda.presentation.navigation.navigateToAgendaGraph
import com.gkp.agenda.presentation.task.edittask.navigation.navigateToEditDescriptionRoute
import com.gkp.agenda.presentation.task.edittask.navigation.navigateToEditTaskGraph
import com.gkp.agenda.presentation.task.edittask.navigation.navigateToEditTitleRoute
import com.gkp.auth.presentation.navigation.AuthGraph
import com.gkp.auth.presentation.navigation.authGraph
import com.gkp.auth.presentation.navigation.navigateToAuthGraph
import com.gkp.auth.presentation.register.navigation.navigateToRegister

@Composable
fun TaskyNavHost(
    isLoggedIn: Boolean,
) {
    val navController = rememberNavController()
    NavHost(
        startDestination = if (isLoggedIn) AgendaGraph else AuthGraph,
        navController = navController
    ) {
        authGraph(
            onSignUpTextClick = navController::navigateToRegister,
            navigateToAgenda = {
                navController.navigateToAgendaGraph(
                    navOptions = navOptions {
                        popUpTo(AuthGraph) {
                            inclusive = true
                        }
                    }
                )
            },
            onFabBackClick = navController::navigateUp
        )
        agendaGraph(
            onEditTaskTitleBackClick = navController::navigateUp,
            onEditTaskDescriptionBackClick = navController::navigateUp,
            onTaskDescriptionClick = {
                navController.navigateToEditDescriptionRoute()
            },
            onTaskTitleClick = {
                navController.navigateToEditTitleRoute()
            },
            onMenuItemTaskClick = {
                navController.navigateToEditTaskGraph(taskId = 1)
            },
            onLogout = {
                navController.navigateToAuthGraph(
                    navOptions = navOptions {
                        popUpTo(AgendaGraph) {
                            inclusive = true
                        }
                    }
                )
            },
            onClickEditCloseButton = navController::navigateUp,
            navController = navController
        )
    }
}