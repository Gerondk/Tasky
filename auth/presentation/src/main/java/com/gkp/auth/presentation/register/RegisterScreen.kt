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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
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
    onGetStartedButtonClick: () -> Unit,
    onFabBackClick: () -> Unit
) {
    val viewModel = koinNavViewModel<RegisterViewModel>()

    RegisterScreen(
        onGetStartedButtonClick = onGetStartedButtonClick,
        onFabBackClick = onFabBackClick
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun RegisterScreen(
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
            val nameTextFieldState = rememberTextFieldState()
            val emailTextFieldState = rememberTextFieldState()
            val passwordTextFieldState = rememberTextFieldState()

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
                    textState = nameTextFieldState
                )
                TaskyTextField(
                    modifier = Modifier.fillMaxWidth(),
                    hintText = stringResource(R.string.email_address_hint),
                    textState = emailTextFieldState
                )

                TaskyPasswordTextField(
                    modifier = Modifier.fillMaxWidth(),
                    hintText = stringResource(R.string.password_hint),
                    textState = passwordTextFieldState,
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
            onGetStartedButtonClick = {},
            onFabBackClick = {}
        )
    }
}