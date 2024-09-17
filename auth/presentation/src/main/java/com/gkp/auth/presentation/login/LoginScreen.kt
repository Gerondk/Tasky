package com.gkp.auth.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gkp.auth.presentation.R
import com.gkp.auth.presentation.components.AuthBlackBackground
import com.gkp.auth.presentation.components.PrimaryButton
import com.gkp.auth.presentation.components.TaskyPasswordTextField
import com.gkp.auth.presentation.components.TaskyTextField
import com.gkp.core.designsystem.theme.Inter
import com.gkp.core.designsystem.theme.TaskyTextFieldColor
import com.gkp.core.designsystem.theme.TaskyTheme
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun LoginScreen(
    modifier: Modifier = Modifier,
    onSignUpTextClick: () -> Unit
) {
    val viewModel = koinViewModel<LoginViewModel>()
    val state by viewModel.loginState.collectAsStateWithLifecycle()

    LoginScreen(
        state,
        onLoginButtonClick = viewModel::onLoginButtonClick,
        onSignUpTextClick = onSignUpTextClick,
    )
}

@Composable
private fun LoginScreen(
    state: LoginState,
    onLoginButtonClick: () -> Unit,
    onSignUpTextClick: () -> Unit
) {
    AuthBlackBackground(title = stringResource(R.string.login_welcome_back)) {
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
                hintText = stringResource(R.string.email_address_hint),
                textState = state.emailTextFieldState,
                endIcon = if (state.isEmailValid) Icons.Default.Check else null,
                endIconContentDescription = if (state.isEmailValid) stringResource(R.string.valid_email) else null
            )
            TaskyPasswordTextField(
                modifier = Modifier.fillMaxWidth(),
                hintText = stringResource(R.string.password_hint),
                textState = state.passwordTextFieldState
            )
            PrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                buttonLabel = stringResource(R.string.login_button_label),
                onClick = onLoginButtonClick,
                textStyle = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.weight(1f))
            val annotatedString = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontFamily = Inter,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                ) {
                    append(stringResource(R.string.dont_have_an_account_text) + " ")
                    val link = LinkAnnotation.Clickable(
                        tag ="SignUp",
                        linkInteractionListener = {
                          onSignUpTextClick()
                        },
                        styles = TextLinkStyles(
                            style = SpanStyle(
                                color = Color.Blue,
                                fontFamily = Inter
                            )
                        )
                    )
                    withLink(link = link) {
                        append(stringResource(R.string.sign_up))
                    }
                }
            }
            Text(
                text = annotatedString,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = TaskyTextFieldColor
                )
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@PreviewLightDark
@Composable
private fun LoginScreenPreview() {
    TaskyTheme {
        LoginScreen(
            state = LoginState(),
            onLoginButtonClick = {},
            onSignUpTextClick = {}
        )
    }
}



