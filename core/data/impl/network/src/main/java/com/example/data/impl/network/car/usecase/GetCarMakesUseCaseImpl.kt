package com.example.data.impl.network.car.usecase

import com.example.data.api.car.model.CarMakeDataModel
import com.example.data.api.car.repository.CarRepository
import com.example.data.api.car.usecase.GetCarMakesUseCase
import javax.inject.Inject

class GetCarMakesUseCaseImpl @Inject constructor(
    private val repository: CarRepository
) : GetCarMakesUseCase {

    override suspend fun invoke(year: Int?): List<CarMakeDataModel> =
        repository.getMakes(year)
}

