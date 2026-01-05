package com.example.data.impl.network.car.usecase

import com.example.data.api.car.model.CarTrimDataModel
import com.example.data.api.car.repository.CarRepository
import com.example.data.api.car.usecase.GetCarTrimsUseCase
import javax.inject.Inject

class GetCarTrimsUseCaseImpl @Inject constructor(
    private val repository: CarRepository
) : GetCarTrimsUseCase {

    override suspend fun invoke(
        make: String?,
        model: String?,
        year: Int?,
        body: String?,
        keyword: String?
    ): List<CarTrimDataModel> =
        repository.getTrims(make, model, year, body, keyword)
}

