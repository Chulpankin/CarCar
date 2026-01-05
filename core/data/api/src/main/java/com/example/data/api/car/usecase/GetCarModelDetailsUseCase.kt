package com.example.data.api.car.usecase

import com.example.data.api.car.model.CarModelDetailsDataModel

interface GetCarModelDetailsUseCase {

    suspend operator fun invoke(modelId: String): CarModelDetailsDataModel
}

