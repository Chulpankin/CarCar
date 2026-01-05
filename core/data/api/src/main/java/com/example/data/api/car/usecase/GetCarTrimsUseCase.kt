package com.example.data.api.car.usecase

import com.example.data.api.car.model.CarTrimDataModel

interface GetCarTrimsUseCase {

    suspend operator fun invoke(
        make: String? = null,
        model: String? = null,
        year: Int? = null,
        body: String? = null,
        keyword: String? = null
    ): List<CarTrimDataModel>
}

