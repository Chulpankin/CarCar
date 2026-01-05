package com.example.data.api.car.repository

import com.example.data.api.car.model.CarMakeDataModel
import com.example.data.api.car.model.CarModelDataModel
import com.example.data.api.car.model.CarModelDetailsDataModel
import com.example.data.api.car.model.CarTrimDataModel
import com.example.data.api.car.model.CarYearsDataModel

interface CarRepository {

    suspend fun getYears(): CarYearsDataModel

    suspend fun getMakes(year: Int? = null): List<CarMakeDataModel>

    suspend fun getModels(
        make: String,
        year: Int? = null,
        body: String? = null
    ): List<CarModelDataModel>

    suspend fun getTrims(
        make: String? = null,
        model: String? = null,
        year: Int? = null,
        body: String? = null,
        keyword: String? = null
    ): List<CarTrimDataModel>

    suspend fun getModelById(modelId: String): CarModelDetailsDataModel
}

