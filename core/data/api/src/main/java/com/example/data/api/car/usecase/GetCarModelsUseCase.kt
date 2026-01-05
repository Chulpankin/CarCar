package com.example.data.api.car.usecase

import com.example.data.api.car.model.CarModelDataModel

interface GetCarModelsUseCase {

    suspend operator fun invoke(
        make: String,
        year: Int? = null,
        body: String? = null
    ): List<CarModelDataModel>
}

