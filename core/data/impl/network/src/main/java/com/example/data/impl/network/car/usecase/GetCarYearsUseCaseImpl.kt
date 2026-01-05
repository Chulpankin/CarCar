package com.example.data.impl.network.car.usecase

import com.example.data.api.car.model.CarYearsDataModel
import com.example.data.api.car.repository.CarRepository
import com.example.data.api.car.usecase.GetCarYearsUseCase
import javax.inject.Inject

class GetCarYearsUseCaseImpl @Inject constructor(
    private val repository: CarRepository
) : GetCarYearsUseCase {

    override suspend fun invoke(): CarYearsDataModel =
        repository.getYears()
}

