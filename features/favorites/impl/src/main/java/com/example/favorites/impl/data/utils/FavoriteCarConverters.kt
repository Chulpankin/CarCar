package com.example.favorites.impl.data.utils

import com.example.carlist.api.domain.model.CarDomainModel
import com.example.favorites.api.domain.model.FavoriteCarDomainModel
import com.example.favorites.impl.data.model.FavoriteCarDataModel

internal fun FavoriteCarDataModel.toDomainModel(): FavoriteCarDomainModel =
    FavoriteCarDomainModel(
        id = id,
        modelId = modelId,
        makeId = makeId,
        makeName = makeName,
        makeCountry = makeCountry,
        modelName = modelName,
        modelTrim = modelTrim,
        modelYear = modelYear,
        modelBody = modelBody,
        modelEnginePowerHp = modelEnginePowerHp,
        modelDrive = modelDrive,
        isFavorite = isFavorite
    )

internal fun CarDomainModel.toDataModel(): FavoriteCarDataModel =
    FavoriteCarDataModel(
        id = id,
        modelId = modelId,
        makeId = makeId,
        makeName = makeName,
        makeCountry = makeCountry,
        modelName = modelName,
        modelTrim = modelTrim,
        modelYear = modelYear,
        modelBody = modelBody,
        modelEnginePowerHp = modelEnginePowerHp,
        modelDrive = modelDrive,
        isFavorite = true
    )

internal fun FavoriteCarDomainModel.toFavoriteCarDataModel(): FavoriteCarDataModel =
    FavoriteCarDataModel(
        id = id,
        modelId = modelId,
        makeId = makeId,
        makeName = makeName,
        makeCountry = makeCountry,
        modelName = modelName,
        modelTrim = modelTrim,
        modelYear = modelYear,
        modelBody = modelBody,
        modelEnginePowerHp = modelEnginePowerHp,
        modelDrive = modelDrive,
        isFavorite = isFavorite
    )

