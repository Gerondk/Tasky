package com.gkp.auth.presentation.register

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gkp.auth.presentation.R
import com.gkp.auth.presentation.components.AuthBlackBackground
import com.gkp.auth.presentation.components.PrimaryButton
import com.gkp.auth.presentation.components.TaskyPasswordTextField
import com.gkp.auth.presentation.components.TaskyTextField
import com.gkp.core.designsystem.theme.TaskyTheme
import org.koin.androidx.compose.navigation.koinNavViewModel

@Composable
internal fun RegisterScreen(
    modifier: Modifier = Modifier,
    onFabBackClick: () -> Unit
) {
    val viewModel = koinNavViewModel<RegisterViewModel>()
    val state by viewModel.registerStateFlow.collectAsStateWithLifecycle()

    RegisterScreen(
        state,
        onGetStartedButtonClick = viewModel::onGetStatedClick,
        onFabBackClick = onFabBackClick
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun RegisterScreen(
    state: RegisterState,
    onGetStartedButtonClick: () -> Unit,
    onFabBackClick: () -> Unit
) {
    Scaffold(
        floatingActionButton = {
           FloatingActionButton(
               containerColor = MaterialTheme.colorScheme.primary,
               shape = RoundedCornerShape(10.dp),
               onClick = onFabBackClick
           ) {
               Icon(
                   imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
                   contentDescription = "Back to login"
               )
           }
        },
        floatingActionButtonPosition = FabPosition.Start
    ) {
        AuthBlackBackground(title = "Create your account") {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .safeContentPadding()
                    .fillMaxHeight(0.8f)
                    .align(Alignment.BottomEnd)
                    .background(
                        color = MaterialTheme.colorScheme.background,
                        shape = RoundedCornerShape(
                            topStart = 30.dp,
                            topEnd = 30.dp
                        )
                    )
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                TaskyTextField(
                    modifier = Modifier.fillMaxWidth(),
                    hintText = stringResource(R.string.name_hint),
                    textState = state.fullNameTextState
                )
                TaskyTextField(
                    modifier = Modifier.fillMaxWidth(),
                    hintText = stringResource(R.string.email_address_hint),
                    textState = state.emailTextState
                )

                TaskyPasswordTextField(
                    modifier = Modifier.fillMaxWidth(),
                    hintText = stringResource(R.string.password_hint),
                    textState = state.passwordTextState,
                )
                PrimaryButton(
                    modifier = Modifier.fillMaxWidth(),
                    buttonLabel = stringResource(R.string.register_get_started),
                    onClick = onGetStartedButtonClick,
                    textStyle = MaterialTheme.typography.bodyMedium
                )
            }
        }

    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@PreviewLightDark
@Composable
private fun RegisterScreenPreview() {
    TaskyTheme {
        RegisterScreen(
            state = RegisterState(),
            onGetStartedButtonClick = {},
            onFabBackClick = {}
        )
    }
}