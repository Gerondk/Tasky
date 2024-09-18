package com.gkp.auth.presentation.login.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gkp.auth.presentation.login.LoginScreen
import kotlinx.serialization.Serializable

@Serializable
object LoginRoute

fun NavGraphBuilder.loginScreen(
    onSignUpTextClick: () -> Unit,
    navigateToAgenda: () -> Unit
) {
    composable<LoginRoute> {
        LoginScreen(
            onSignUpTextClick = onSignUpTextClick,
            navigateToAgenda = navigateToAgenda
        )
    }
}

fun NavController.navigateToLogin() {
    navigate(LoginRoute)
}
