package com.example.favorites.api.domain.model

class FavoriteCarDomainModel(
    val id: String = "",
    val modelId: String = "",
    val makeId: String = "",
    val makeName: String = "",
    val makeCountry: String = "",
    val modelName: String = "",
    val modelTrim: String = "",
    val modelYear: String = "",
    val modelBody: String? = null,
    val modelEnginePowerHp: String? = null,
    val modelDrive: String? = null,
    val isFavorite: Boolean = true
)

