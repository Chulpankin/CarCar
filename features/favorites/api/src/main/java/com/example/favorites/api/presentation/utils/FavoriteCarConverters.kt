package com.example.favorites.api.presentation.utils

import com.example.favorites.api.domain.model.FavoriteCarDomainModel
import com.example.ui.model.CarUiModel

fun FavoriteCarDomainModel.toUiModel() =
    CarUiModel(
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
        modelDrive = modelDrive
    )

