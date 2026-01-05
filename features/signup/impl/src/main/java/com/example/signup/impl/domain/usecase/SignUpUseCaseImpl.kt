package com.example.signup.impl.domain.usecase

import com.example.signup.api.domain.usecase.SignUpUseCase
import com.example.data.api.user.AuthService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SignUpUseCaseImpl @Inject constructor(
    private val authService: AuthService,
    private val coroutineDispatcher: CoroutineDispatcher
) : SignUpUseCase {

    override suspend fun invoke(email: String, password: String) {
        withContext(coroutineDispatcher) {
            authService.signUp(email, password)
        }
    }
}

