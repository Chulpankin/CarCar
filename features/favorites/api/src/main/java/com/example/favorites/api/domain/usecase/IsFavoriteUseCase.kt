package com.example.favorites.api.domain.usecase

interface IsFavoriteUseCase {

    suspend operator fun invoke(carId: String): Boolean
}

