package com.example.favorites.impl.domain.usecase

import com.example.favorites.api.domain.repository.FavoriteCarRepository
import com.example.favorites.api.domain.usecase.IsFavoriteUseCase
import javax.inject.Inject

class IsFavoriteUseCaseImpl @Inject constructor(
    private val favoriteCarRepository: FavoriteCarRepository
) : IsFavoriteUseCase {

    override suspend operator fun invoke(carId: String): Boolean =
        favoriteCarRepository.isFavorite(carId)
}

