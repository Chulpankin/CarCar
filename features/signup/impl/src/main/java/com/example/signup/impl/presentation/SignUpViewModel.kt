package com.example.signup.impl.presentation

import android.util.Patterns
import androidx.lifecycle.viewModelScope
import com.example.common.exceptions.AppException
import com.example.common.utils.AppExceptionHandler
import com.example.common.utils.ResourceManager
import com.example.common.utils.runSuspendCatching
import com.example.signup.impl.R
import com.example.data.api.analytics.datasource.AnalyticsService
import com.example.signup.api.domain.usecase.SignUpUseCase
import com.example.signup.api.navigation.SignUpRouter
import com.example.signup.impl.presentation.model.SignUpAction
import com.example.signup.impl.presentation.model.SignUpEvent
import com.example.signup.impl.presentation.model.SignUpState
import com.example.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    private val router: SignUpRouter,
    private val signUpUseCase: SignUpUseCase,
    private val appExceptionHandler: AppExceptionHandler,
    private val resourceManager: ResourceManager,
    private val analyticsService: AnalyticsService
) : BaseViewModel<SignUpState, SignUpEvent, SignUpAction>(
    initialState = SignUpState()
) {

    init {
        analyticsService.logScreenView("sign_up", "SignUpViewModel")
    }

    override fun obtainEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.SignUpClick -> signUp()
            is SignUpEvent.SignInClick -> router.navigateToSignIn()
            is SignUpEvent.EmailChanged -> updateEmail(event.email)
            is SignUpEvent.PasswordChanged -> updatePassword(event.password)
            is SignUpEvent.ConfirmPasswordChanged -> updateConfirmPassword(event.confirmPassword)
        }
    }

    private fun signUp() {
        _uiState.value.let { state ->
            val trimmedEmail = state.email.trim()
            val trimmedPassword = state.password.trim()
            val trimmedConfirmPassword = state.confirmPassword.trim()
            
            val emailValid = isValidEmail(trimmedEmail)
            val passwordValid = trimmedPassword.length >= 6
            val passwordsMatch = trimmedPassword == trimmedConfirmPassword
            val confirmPasswordValid = passwordsMatch && passwordValid
            
            if (!emailValid || !passwordValid || !passwordsMatch) {
                _uiState.value = state.copy(
                    hasAttemptedSubmit = true,
                    hasEmailError = !emailValid,
                    hasPasswordError = !passwordValid,
                    hasConfirmPasswordError = !confirmPasswordValid,
                    isPasswordMismatch = !passwordsMatch,
                    password = if (!passwordValid || !passwordsMatch) "" else state.password,
                    confirmPassword = if (!passwordsMatch) "" else state.confirmPassword
                )
                if (!passwordsMatch) {
                    viewModelScope.launch {
                        _actionsFlow.emit(SignUpAction.ShowMessage(
                            resourceManager.getString(R.string.passwords_do_not_match)
                        ))
                    }
                }
                return
            }

            _uiState.value = state.copy(
                email = trimmedEmail,
                password = trimmedPassword,
                confirmPassword = trimmedConfirmPassword,
                isLoading = true,
                hasAttemptedSubmit = true
            )

            viewModelScope.launch {
                runSuspendCatching(appExceptionHandler) {
                    signUpUseCase.invoke(trimmedEmail, trimmedPassword)
                }.onSuccess {
                    _uiState.value = _uiState.value.copy(isLoading = false)
                    router.navigateToFeed()
                }.onFailure {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        password = "",
                        confirmPassword = "",
                        isInvalidCredentials = it is AppException.AuthInvalidCredentialsException
                    )
                    _actionsFlow.emit(SignUpAction.ShowMessage(it.message.orEmpty()))
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
            val trimmedConfirmPassword = state.confirmPassword.trim()
            val hasError = if (state.hasAttemptedSubmit) {
                trimmedPassword.isNotEmpty() && trimmedPassword.length < 6
            } else {
                false
            }
            val passwordsMatch = trimmedPassword == trimmedConfirmPassword
            val hasConfirmError = if (state.hasAttemptedSubmit && trimmedConfirmPassword.isNotEmpty()) {
                !passwordsMatch || trimmedConfirmPassword.length < 6
            } else {
                false
            }
            _uiState.value = state.copy(
                password = password,
                hasPasswordError = hasError,
                hasConfirmPasswordError = hasConfirmError,
                isPasswordMismatch = trimmedConfirmPassword.isNotEmpty() && !passwordsMatch,
                isInvalidCredentials = false
            )
        }
    }

    private fun updateConfirmPassword(confirmPassword: String) {
        _uiState.value.let { state ->
            val trimmedConfirmPassword = confirmPassword.trim()
            val trimmedPassword = state.password.trim()
            val passwordsMatch = trimmedConfirmPassword == trimmedPassword
            val hasError = if (state.hasAttemptedSubmit) {
                trimmedConfirmPassword.isNotEmpty() && (!passwordsMatch || trimmedConfirmPassword.length < 6)
            } else {
                false
            }
            _uiState.value = state.copy(
                confirmPassword = confirmPassword,
                hasConfirmPasswordError = hasError,
                isPasswordMismatch = trimmedConfirmPassword.isNotEmpty() && !passwordsMatch,
                isInvalidCredentials = false
            )
        }
    }

    private fun isValidEmail(email: String) =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

