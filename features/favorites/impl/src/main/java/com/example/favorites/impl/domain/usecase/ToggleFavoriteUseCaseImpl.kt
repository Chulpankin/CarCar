package com.example.favorites.impl.domain.usecase

import com.example.carlist.api.domain.model.CarDomainModel
import com.example.favorites.api.domain.model.FavoriteCarDomainModel
import com.example.favorites.api.domain.repository.FavoriteCarRepository
import com.example.favorites.api.domain.usecase.ToggleFavoriteUseCase
import javax.inject.Inject

class ToggleFavoriteUseCaseImpl @Inject constructor(
    private val favoriteCarRepository: FavoriteCarRepository
) : ToggleFavoriteUseCase {

    override suspend operator fun invoke(car: CarDomainModel, isFavorite: Boolean) {
        val favoriteCar = FavoriteCarDomainModel(
            id = car.id,
            modelId = car.modelId,
            makeId = car.makeId,
            makeName = car.makeName,
            makeCountry = car.makeCountry,
            modelName = car.modelName,
            modelTrim = car.modelTrim,
            modelYear = car.modelYear,
            modelBody = car.modelBody,
            modelEnginePowerHp = car.modelEnginePowerHp,
            modelDrive = car.modelDrive,
            isFavorite = true
        )
        if (isFavorite) {
            favoriteCarRepository.removeFromFavorites(car.id)
        } else {
            favoriteCarRepository.addToFavorites(favoriteCar)
        }
    }
}

