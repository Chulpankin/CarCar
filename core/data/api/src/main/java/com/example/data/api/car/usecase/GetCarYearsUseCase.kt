package com.example.data.api.car.usecase

import com.example.data.api.car.model.CarYearsDataModel

interface GetCarYearsUseCase {

    suspend operator fun invoke(): CarYearsDataModel
}

