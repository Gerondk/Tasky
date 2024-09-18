package com.gkp.tasky

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.gkp.core.designsystem.theme.TaskyTheme
import com.gkp.tasky.nagivation.TaskyNavHost
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModel()
    private var navHostStartDestinationSet by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.mainAppState.sessionStorageLoading
            }
        }
        enableEdgeToEdge()

        setContent {
            TaskyTheme {
                if (!viewModel.mainAppState.sessionStorageLoading) {
                    TaskyNavHost(
                        isLoggedIn = viewModel.mainAppState.isLoggedIn,
                    )
                }
            }
        }
    }
}

