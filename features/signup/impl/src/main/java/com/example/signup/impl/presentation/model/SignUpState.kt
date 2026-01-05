package com.example.signup.impl.presentation.model

data class SignUpState(
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isInvalidCredentials: Boolean = false,
    val isLoading: Boolean = false,
    val hasAttemptedSubmit: Boolean = false,
    val hasEmailError: Boolean = false,
    val hasPasswordError: Boolean = false,
    val hasConfirmPasswordError: Boolean = false,
    val isPasswordMismatch: Boolean = false,
) {
    val isFormValid: Boolean
        get() = email.trim().isNotEmpty() && password.trim().isNotEmpty() && confirmPassword.trim().isNotEmpty()
}

