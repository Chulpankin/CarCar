package com.example.signin.impl.presentation

import android.util.Patterns
import androidx.lifecycle.viewModelScope
import com.example.common.exceptions.AppException
import com.example.common.utils.AppExceptionHandler
import com.example.common.utils.runSuspendCatching
import com.example.data.api.analytics.datasource.AnalyticsService
import com.example.signin.api.domain.usecase.IsUserAuthorizedUseCase
import com.example.signin.api.domain.usecase.SignInUseCase
import com.example.signin.api.navigation.SignInRouter
import com.example.signin.impl.presentation.model.SignInAction
import com.example.signin.impl.presentation.model.SignInState
import com.example.signin.impl.presentation.model.SignInEvent
import com.example.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignInViewModel @Inject constructor(
    private val router: SignInRouter,
    private val isUserAuthorizedUseCase: IsUserAuthorizedUseCase,
    private val signInUseCase: SignInUseCase,
    private val appExceptionHandler: AppExceptionHandler,
    private val analyticsService: AnalyticsService
) : BaseViewModel<SignInState, SignInEvent, SignInAction>(
    initialState = SignInState()
) {

    init {
        analyticsService.logScreenView("sign_in", "SignInViewModel")
        checkUserAuthorized()
    }

    override fun obtainEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.SignInClick -> signIn()
            is SignInEvent.SignUpClick -> router.navigateToSignUp()
            is SignInEvent.EmailChanged -> updateEmail(event.email)
            is SignInEvent.PasswordChanged -> updatePassword(event.password)
        }
    }

    private fun checkUserAuthorized() {
        viewModelScope.launch {
            runSuspendCatching(appExceptionHandler) {
                isUserAuthorizedUseCase.invoke()
            }.onSuccess {
                it.takeIf { it }?.let { router.navigateToMain() }
            }
        }
    }

    private fun signIn() {
        _uiState.value.let { state ->
            val trimmedEmail = state.email.trim()
            val trimmedPassword = state.password.trim()
            
            val emailValid = isValidEmail(trimmedEmail)
            val passwordValid = trimmedPassword.length >= 6
            
            if (!emailValid || !passwordValid) {
                _uiState.value = state.copy(
                    hasAttemptedSubmit = true,
                    hasEmailError = !emailValid,
                    hasPasswordError = !passwordValid,
                    password = if (!passwordValid) "" else state.password
                )
                return
            }
            
            _uiState.value = state.copy(
                email = trimmedEmail,
                password = trimmedPassword,
                isLoading = true,
                hasAttemptedSubmit = true
            )

            viewModelScope.launch {
                runSuspendCatching(appExceptionHandler) {
                    signInUseCase.invoke(trimmedEmail, trimmedPassword)
                }.onSuccess {
                    _uiState.value = _uiState.value.copy(isLoading = false)
                    router.navigateToMain()
                }.onFailure {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        password = "",
                        isInvalidCredentials = it is AppException.AuthInvalidCredentialsException
                    )
                    _actionsFlow.emit(SignInAction.ShowMessage(it.message.orEmpty()))
                }
            }
        }
    }

    private fun updateEmail(email: String) {
        _uiState.value.let { state ->
            val trimmedEmail = email.trim()
            val hasError = if (state.hasAttemptedSubmit) {
                trimmedEmail.isNotEmpty() && !isValidEmail(trimmedEmail)
            } else {
                false
            }
            _uiState.value = state.copy(
                email = email,
                hasEmailError = hasError,
                isInvalidCredentials = false
            )
        }
    }

    private fun updatePassword(password: String) {
        _uiState.value.let { state ->
            val trimmedPassword = password.trim()
            val hasError = if (state.hasAttemptedSubmit) {
                trimmedPassword.isNotEmpty() && trimmedPassword.length < 6
            } else {
                false
            }
            _uiState.value = state.copy(
                password = password,
                hasPasswordError = hasError,
                isInvalidCredentials = false
            )
        }
    }

    private fun isValidEmail(email: String) =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()
}
