package com.example.signin.impl.presentation.model

sealed interface SignInAction {
    data class ShowMessage(val message: String) : SignInAction
}

