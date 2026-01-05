package com.example.data.api.car.usecase

import com.example.data.api.car.model.CarMakeDataModel

interface GetCarMakesUseCase {

    suspend operator fun invoke(year: Int? = null): List<CarMakeDataModel>
}

