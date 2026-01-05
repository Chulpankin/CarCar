package com.example.favorites.api.domain.usecase

import com.example.carlist.api.domain.model.CarDomainModel

interface ToggleFavoriteUseCase {

    suspend operator fun invoke(car: CarDomainModel, isFavorite: Boolean)
}

