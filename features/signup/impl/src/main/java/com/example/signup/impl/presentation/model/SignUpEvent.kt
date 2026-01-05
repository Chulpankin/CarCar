package com.example.signup.impl.presentation.model

sealed interface SignUpEvent {
    data object SignUpClick : SignUpEvent
    data object SignInClick : SignUpEvent
    data class EmailChanged(val email: String) : SignUpEvent
    data class PasswordChanged(val password: String) : SignUpEvent
    data class ConfirmPasswordChanged(val confirmPassword: String) : SignUpEvent
}

