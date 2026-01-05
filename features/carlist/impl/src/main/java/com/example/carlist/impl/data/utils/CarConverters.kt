package com.example.carlist.impl.data.utils

import com.example.data.api.car.model.CarTrimDataModel
import com.example.carlist.api.domain.model.CarDomainModel

internal fun CarTrimDataModel.toDomainModel(
    makeCountryMap: Map<String, String> = emptyMap()
): CarDomainModel =
    CarDomainModel(
        id = "${modelId}_${modelYear}_${modelTrim}",
        modelId = modelId,
        makeId = modelMakeId,
        makeName = "",
        makeCountry = makeCountryMap[modelMakeId] ?: "",
        modelName = modelName,
        modelTrim = modelTrim,
        modelYear = modelYear
    )

