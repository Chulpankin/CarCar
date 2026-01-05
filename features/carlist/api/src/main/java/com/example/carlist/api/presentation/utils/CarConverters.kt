package com.example.carlist.api.presentation.utils

import com.example.carlist.api.domain.model.CarDomainModel
import com.example.ui.model.CarUiModel

fun CarDomainModel.toUiModel() =
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

