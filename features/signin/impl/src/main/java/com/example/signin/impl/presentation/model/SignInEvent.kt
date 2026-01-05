package com.example.signin.impl.presentation.model

sealed interface SignInEvent {
    data object SignInClick : SignInEvent
    data object SignUpClick : SignInEvent
    data class EmailChanged(val email: String) : SignInEvent
    data class PasswordChanged(val password: String) : SignInEvent
}