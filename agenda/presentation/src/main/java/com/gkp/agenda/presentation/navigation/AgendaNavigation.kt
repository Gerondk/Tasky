package com.gkp.agenda.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.gkp.agenda.presentation.AgendaScreen
import kotlinx.serialization.Serializable


@Serializable
data object AgendaRoute

fun NavController.navigateToAgendaRoute(navOptions: NavOptions? = null) {
    navigate(AgendaRoute, navOptions)
}

fun NavGraphBuilder.agendaScreen() {
    composable<AgendaRoute> {
        AgendaScreen()
    }
}

