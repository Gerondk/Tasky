package com.gkp.tasky.nagivation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.gkp.auth.presentation.login.navigation.LoginRoute
import com.gkp.auth.presentation.login.navigation.loginScreen
import com.gkp.auth.presentation.register.navigation.navigateToRegister
import com.gkp.auth.presentation.register.navigation.registerScreen

@Composable
fun TaskyNavHost() {
    val navController = rememberNavController()
    NavHost(
        startDestination = LoginRoute,
        navController = navController
    ) {
        loginScreen(
            onLoginButtonClick = {},
            onSignUpTextClick =  navController::navigateToRegister
        )
        registerScreen(
            onGetStartedButtonClick = {},
            onFabBackClick = navController::navigateUp
        )
    }
}