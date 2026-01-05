package com.example.signin.impl.presentation.composable

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
import com.example.signin.impl.R
import com.example.signin.impl.presentation.SignInViewModel
import com.example.signin.impl.presentation.model.SignInAction
import com.example.signin.impl.presentation.model.SignInState
import com.example.signin.impl.presentation.model.SignInEvent
import com.example.ui.themes.ButtonTopSpacing
import com.example.ui.themes.OnSurfaceTextAlpha
import com.example.ui.themes.TextFieldSpacing
import com.example.ui.view.composable.CarCarScaffold
import com.example.ui.view.composable.LoadingScreen
import com.example.ui.view.composable.PrimaryButton
import com.example.ui.view.composable.TextFieldWithIcon

@Composable
fun SignInScreen(viewModel: SignInViewModel) {
    val snackbarHostState = remember { SnackbarHostState() }
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.actionsFlow.collect { action ->
            when (action) {
                is SignInAction.ShowMessage ->
                    snackbarHostState.showSnackbar(message = action.message)
            }
        }
    }

    CarCarScaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { paddingValues ->
        SignInContent(
            email = uiState.email,
            password = uiState.password,
            isInvalidCredentials = uiState.isInvalidCredentials,
            isFormValid = uiState.isFormValid,
            hasEmailError = uiState.hasEmailError,
            hasPasswordError = uiState.hasPasswordError,
            paddingValues = paddingValues,
            onEmailChange = { viewModel.obtainEvent(SignInEvent.EmailChanged(it)) },
            onPasswordChange = { viewModel.obtainEvent(SignInEvent.PasswordChanged(it)) },
            onSignUpClick = { viewModel.obtainEvent(SignInEvent.SignUpClick) },
            onSignInClick = { viewModel.obtainEvent(SignInEvent.SignInClick) },
        )

        LoadingScreen(isLoading = uiState.isLoading)
    }
}

@Composable
private fun SignInContent(
    email: String,
    password: String,
    isInvalidCredentials: Boolean,
    isFormValid: Boolean,
    hasEmailError: Boolean,
    hasPasswordError: Boolean,
    paddingValues: PaddingValues,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignUpClick: () -> Unit,
    onSignInClick: () -> Unit,
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
        CredentialsAndSignIn(
            email = email,
            password = password,
            isInvalidCredentials = isInvalidCredentials,
            isFormValid = isFormValid,
            hasEmailError = hasEmailError,
            hasPasswordError = hasPasswordError,
            onEmailChange = onEmailChange,
            onPasswordChange = onPasswordChange,
            onSignInClick = onSignInClick,
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = buildAnnotatedString {
                append(stringResource(R.string.don_t_have_an_account))

                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = MaterialTheme.typography.bodyMedium.fontWeight
                    ),
                ) {
                    append(stringResource(R.string.sign_up))
                }
            },
            style = MaterialTheme.typography.bodyLarge.copy(
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = OnSurfaceTextAlpha)
            ),
            modifier = Modifier.fillMaxWidth().clickable { onSignUpClick() }
        )
        Spacer(modifier = Modifier.fillMaxHeight(fraction = 0.74f))
    }
}

@Composable
private fun TitleText(modifier: Modifier = Modifier) {
    Spacer(modifier = modifier.height(20.dp))
    Text(
        text = stringResource(id = R.string.sign_in),
        style = MaterialTheme.typography.headlineSmall.copy(
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold
        ),
        modifier = modifier.padding(vertical = 16.dp)
    )
    Text(
        text = stringResource(R.string.welcome_back),
        style = MaterialTheme.typography.headlineMedium.copy(
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold
        ),
        modifier = modifier.padding(vertical = 16.dp)
    )
    Text(
        text = stringResource(R.string.signin_desc),
        style = MaterialTheme.typography.bodyLarge.copy(
            color = MaterialTheme.colorScheme.onBackground
        ),
        textAlign = TextAlign.Center,
        modifier = modifier.padding(bottom = 24.dp)
    )
}

@Composable
private fun CredentialsAndSignIn(
    email: String,
    password: String,
    isInvalidCredentials: Boolean,
    isFormValid: Boolean,
    hasEmailError: Boolean,
    hasPasswordError: Boolean,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignInClick: () -> Unit,
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
    Spacer(modifier = Modifier.height(ButtonTopSpacing))
    PrimaryButton(
        text = stringResource(R.string.sign_in),
        onClick = onSignInClick,
        modifier = modifier,
        enabled = isFormValid
    )
}

