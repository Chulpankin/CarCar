package com.example.signup.api.domain.usecase

interface SignUpUseCase {

    suspend operator fun invoke(email: String, password: String)
}

