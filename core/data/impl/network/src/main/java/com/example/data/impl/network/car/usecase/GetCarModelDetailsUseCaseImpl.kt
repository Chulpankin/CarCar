package com.example.data.impl.network.car.usecase

import com.example.data.api.car.model.CarModelDetailsDataModel
import com.example.data.api.car.repository.CarRepository
import com.example.data.api.car.usecase.GetCarModelDetailsUseCase
import javax.inject.Inject

class GetCarModelDetailsUseCaseImpl @Inject constructor(
    private val repository: CarRepository
) : GetCarModelDetailsUseCase {

    override suspend fun invoke(modelId: String): CarModelDetailsDataModel =
        repository.getModelById(modelId)
}

