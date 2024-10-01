package com.gkp.tasky.nagivation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.gkp.agenda.presentation.navigation.AgendaScreenRoute
import com.gkp.agenda.presentation.navigation.agendaScreen
import com.gkp.agenda.presentation.navigation.navigateToAgendaRoute
import com.gkp.agenda.presentation.navigation.navigateToEditDescriptionRoute
import com.gkp.agenda.presentation.navigation.navigateToEditTaskRoute
import com.gkp.agenda.presentation.navigation.navigateToEditTitleRoute
import com.gkp.auth.presentation.login.navigation.LoginRoute
import com.gkp.auth.presentation.login.navigation.loginScreen
import com.gkp.auth.presentation.login.navigation.navigateToLogin
import com.gkp.auth.presentation.register.navigation.navigateToRegister
import com.gkp.auth.presentation.register.navigation.registerScreen

@Composable
fun TaskyNavHost(
    isLoggedIn: Boolean,
) {
    val navController = rememberNavController()
    NavHost(
        startDestination = if (isLoggedIn) AgendaScreenRoute else LoginRoute,
        navController = navController
    ) {
        loginScreen(
            onSignUpTextClick =  navController::navigateToRegister,
            navigateToAgenda = {
                navController.navigateToAgendaRoute(
                    navOptions = navOptions {
                        popUpTo(LoginRoute) {
                            inclusive = true
                        }
                    }
                )
            }
        )
        registerScreen(
            onFabBackClick = navController::navigateUp
        )
        agendaScreen(
            onEditTaskTitleBackClick = navController::navigateUp,
            onEditTaskDescriptionBackClick = navController::navigateUp,
            onTaskDescriptionClick = {
                navController.navigateToEditDescriptionRoute()
            },
            onTaskTitleClick = {
                navController.navigateToEditTitleRoute()
            },
            onMenuItemTaskClick = {
                navController.navigateToEditTaskRoute()
            } ,
            onLogout = {
                navController.navigateToLogin(
                    navOptions = navOptions {
                        popUpTo(AgendaScreenRoute) {
                            inclusive = true
                        }
                    }
                )
            }
        )
    }
}