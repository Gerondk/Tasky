package com.gkp.auth.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import com.gkp.auth.presentation.login.navigation.LoginRoute
import com.gkp.auth.presentation.login.navigation.loginScreen
import com.gkp.auth.presentation.register.navigation.registerScreen
import kotlinx.serialization.Serializable

@Serializable
data object AuthGraph

fun NavController.navigateToAuthGraph(navOptions: NavOptions? = null) {
    navigate(AuthGraph, navOptions)
}

fun NavGraphBuilder.authGraph(
    onSignUpTextClick:() -> Unit,
    navigateToAgenda: () -> Unit,
    onFabBackClick: () -> Unit
) {
    navigation<AuthGraph>(
        startDestination = LoginRoute,
    ) {
        loginScreen(
            onSignUpTextClick = onSignUpTextClick,
            navigateToAgenda = navigateToAgenda,
        )

        registerScreen(
            onFabBackClick = onFabBackClick
        )
    }
}