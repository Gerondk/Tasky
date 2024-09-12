package com.gkp.auth.presentation.register.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gkp.auth.presentation.register.RegisterScreen
import kotlinx.serialization.Serializable

@Serializable
data object RegisterRoute

fun NavGraphBuilder.registerScreen(
    onFabBackClick: () -> Unit
) {
    composable<RegisterRoute> {
        RegisterScreen(
            onFabBackClick = onFabBackClick
        )
    }
}

fun NavController.navigateToRegister() {
    navigate(RegisterRoute)
}