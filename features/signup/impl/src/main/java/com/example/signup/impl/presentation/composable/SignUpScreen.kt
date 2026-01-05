package com.example.signup.impl.presentation.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.signup.impl.R
import com.example.signup.impl.presentation.SignUpViewModel
import com.example.signup.impl.presentation.model.SignUpAction
import com.example.signup.impl.presentation.model.SignUpEvent
import com.example.signup.impl.presentation.model.SignUpState
import com.example.ui.themes.ButtonTopSpacing
import com.example.ui.themes.OnSurfaceTextAlpha
import com.example.ui.themes.TextFieldSpacing
import com.example.ui.view.composable.CarCarScaffold
import com.example.ui.view.composable.LoadingScreen
import com.example.ui.view.composable.PrimaryButton
import com.example.ui.view.composable.TextFieldWithIcon

@Composable
fun SignUpScreen(viewModel: SignUpViewModel) {
    val snackbarHostState = remember { SnackbarHostState() }
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.actionsFlow.collect { action ->
            when (action) {
                is SignUpAction.ShowMessage ->
                    snackbarHostState.showSnackbar(message = action.message)
            }
        }
    }

    CarCarScaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { paddingValues ->
        SignUpContent(
            email = uiState.email,
            password = uiState.password,
            confirmPassword = uiState.confirmPassword,
            isInvalidCredentials = uiState.isInvalidCredentials,
            isFormValid = uiState.isFormValid,
            hasEmailError = uiState.hasEmailError,
            hasPasswordError = uiState.hasPasswordError,
            hasConfirmPasswordError = uiState.hasConfirmPasswordError,
            isPasswordMismatch = uiState.isPasswordMismatch,
            paddingValues = paddingValues,
            onEmailChange = { viewModel.obtainEvent(SignUpEvent.EmailChanged(it)) },
            onPasswordChange = { viewModel.obtainEvent(SignUpEvent.PasswordChanged(it)) },
            onConfirmPasswordChange = { viewModel.obtainEvent(SignUpEvent.ConfirmPasswordChanged(it)) },
            onSignInClick = { viewModel.obtainEvent(SignUpEvent.SignInClick) },
            onSignUpClick = { viewModel.obtainEvent(SignUpEvent.SignUpClick) }
        )

        LoadingScreen(isLoading = uiState.isLoading)
    }
}

@Composable
private fun SignUpContent(
    email: String,
    password: String,
    confirmPassword: String,
    isInvalidCredentials: Boolean,
    isFormValid: Boolean,
    hasEmailError: Boolean,
    hasPasswordError: Boolean,
    hasConfirmPasswordError: Boolean,
    isPasswordMismatch: Boolean,
    paddingValues: PaddingValues,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(paddingValues)
            .padding(horizontal = 16.dp)
    ) {
        TitleText()
        CredentialsAndSignUp(
            email = email,
            password = password,
            confirmPassword = confirmPassword,
            isInvalidCredentials = isInvalidCredentials,
            isFormValid = isFormValid,
            hasEmailError = hasEmailError,
            hasPasswordError = hasPasswordError,
            hasConfirmPasswordError = hasConfirmPasswordError,
            isPasswordMismatch = isPasswordMismatch,
            onEmailChange = onEmailChange,
            onPasswordChange = onPasswordChange,
            onConfirmPasswordChange = onConfirmPasswordChange,
            onSignUpClick = onSignUpClick,
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = buildAnnotatedString {
                append(stringResource(R.string.already_have_an_account))

                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = MaterialTheme.typography.bodyMedium.fontWeight
                    ),
                ) {
                    append(stringResource(R.string.sign_in))
                }
            },
            style = MaterialTheme.typography.bodyLarge.copy(
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = OnSurfaceTextAlpha)
            ),
            modifier = Modifier.fillMaxWidth().clickable { onSignInClick() }
        )
        Spacer(modifier = Modifier.fillMaxHeight(fraction = 0.74f))
    }
}

@Composable
private fun TitleText(modifier: Modifier = Modifier) {
    Spacer(modifier = modifier.height(20.dp))
    Text(
        text = stringResource(id = R.string.sign_up),
        style = MaterialTheme.typography.headlineSmall.copy(
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold
        ),
        modifier = modifier.padding(vertical = 16.dp)
    )
    Text(
        text = stringResource(R.string.create_account),
        style = MaterialTheme.typography.headlineMedium.copy(
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold
        ),
        modifier = modifier.padding(vertical = 16.dp)
    )
    Text(
        text = stringResource(R.string.signup_desc),
        style = MaterialTheme.typography.bodyLarge.copy(
            color = MaterialTheme.colorScheme.onBackground
        ),
        textAlign = TextAlign.Center,
        modifier = modifier.padding(bottom = 24.dp)
    )
}

@Composable
private fun CredentialsAndSignUp(
    email: String,
    password: String,
    confirmPassword: String,
    isInvalidCredentials: Boolean,
    isFormValid: Boolean,
    hasEmailError: Boolean,
    hasPasswordError: Boolean,
    hasConfirmPasswordError: Boolean,
    isPasswordMismatch: Boolean,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        TextFieldWithIcon(
            value = email,
            onValueChange = onEmailChange,
            label = stringResource(R.string.email),
            modifier = Modifier.fillMaxWidth(),
            isError = isInvalidCredentials || hasEmailError,
        )
        if (hasEmailError) {
            Text(
                text = stringResource(R.string.email_error),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
    Spacer(modifier = Modifier.height(TextFieldSpacing))
    Column(modifier = modifier.fillMaxWidth()) {
        TextFieldWithIcon(
            value = password,
            onValueChange = onPasswordChange,
            label = stringResource(R.string.password),
            modifier = Modifier.fillMaxWidth(),
            isPassword = true,
            isError = isInvalidCredentials || hasPasswordError
        )
        if (hasPasswordError) {
            Text(
                text = stringResource(R.string.password_error),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
    Spacer(modifier = Modifier.height(TextFieldSpacing))
    Column(modifier = modifier.fillMaxWidth()) {
        TextFieldWithIcon(
            value = confirmPassword,
            onValueChange = onConfirmPasswordChange,
            label = stringResource(R.string.confirm_password),
            modifier = Modifier.fillMaxWidth(),
            isPassword = true,
            isError = isInvalidCredentials || hasConfirmPasswordError
        )
        if (hasConfirmPasswordError) {
            Text(
                text = stringResource(
                    if (isPasswordMismatch) R.string.confirm_password_error
                    else R.string.password_error
                ),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
    Spacer(modifier = Modifier.height(ButtonTopSpacing))
    PrimaryButton(
        text = stringResource(R.string.sign_up),
        onClick = onSignUpClick,
        modifier = modifier,
        enabled = isFormValid
    )
}
