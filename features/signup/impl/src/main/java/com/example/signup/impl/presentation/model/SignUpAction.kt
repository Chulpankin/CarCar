package com.example.signup.impl.presentation.model

sealed interface SignUpAction {
    data class ShowMessage(val message: String) : SignUpAction
}

