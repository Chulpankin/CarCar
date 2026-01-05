package com.example.data.api.car.model

data class CarModelDetailsDataModel(
    val modelId: String,
    val modelMakeId: String,
    val modelName: String,
    val modelYear: String,
    val modelBody: String? = null,
    val modelEnginePowerHp: String? = null,
    val modelDrive: String? = null
)

