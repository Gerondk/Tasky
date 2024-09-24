package com.gkp.agenda.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.koinViewModel

@Composable
fun AgendaScreen(
    onLogout: () -> Unit
) {
    val viewModel = koinViewModel<AgendaViewModel>()

    Box(
        modifier = Modifier.fillMaxSize(),

    ) {
        Button( modifier = Modifier.align(Alignment.TopEnd), onClick = {
            onLogout()
            viewModel.logout()
        }
        ) {
            Text(text = "Logout")
        }
        Text(
            text = "Agenda",
            modifier = Modifier.align(Alignment.Center)
            ,fontSize = 40.sp
        )
    }
}
