package com.example.signup.impl

import com.example.data.api.user.AuthService
import com.example.signup.impl.domain.usecase.SignUpUseCaseImpl
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Test

class SignUpUseCaseTest {
    @Test
    fun `invoke calls signUp on authService`() = runBlocking {
        val authService = mockk<AuthService>(relaxed = true)
        val useCase = SignUpUseCaseImpl(authService, Dispatchers.Unconfined)
        val email = "test@example.com"
        val password = "password"
        useCase.invoke(email, password)
        coVerify(exactly = 1) { authService.signUp(email, password) }
    }
}

