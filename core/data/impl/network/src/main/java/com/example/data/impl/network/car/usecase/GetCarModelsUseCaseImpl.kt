package com.example.data.impl.network.car.usecase

import com.example.data.api.car.model.CarModelDataModel
import com.example.data.api.car.repository.CarRepository
import com.example.data.api.car.usecase.GetCarModelsUseCase
import javax.inject.Inject

class GetCarModelsUseCaseImpl @Inject constructor(
    private val repository: CarRepository
) : GetCarModelsUseCase {

    override suspend fun invoke(
        make: String,
        year: Int?,
        body: String?
    ): List<CarModelDataModel> =
        repository.getModels(make, year, body)
}

