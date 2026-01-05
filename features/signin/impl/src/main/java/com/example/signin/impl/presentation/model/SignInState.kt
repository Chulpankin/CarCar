package com.example.signin.impl.presentation.model

data class SignInState(
    val email: String = "",
    val password: String = "",
    val isInvalidCredentials: Boolean = false,
    val isLoading: Boolean = false,
    val hasAttemptedSubmit: Boolean = false,
    val hasEmailError: Boolean = false,
    val hasPasswordError: Boolean = false,
) {
    val isFormValid: Boolean
        get() = email.trim().isNotEmpty() && password.trim().isNotEmpty()
}

