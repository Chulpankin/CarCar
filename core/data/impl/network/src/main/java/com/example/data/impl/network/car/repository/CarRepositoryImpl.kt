package com.example.data.impl.network.car.repository

import com.example.data.api.car.model.CarMakeDataModel
import com.example.data.api.car.model.CarModelDataModel
import com.example.data.api.car.model.CarModelDetailsDataModel
import com.example.data.api.car.model.CarTrimDataModel
import com.example.data.api.car.model.CarYearsDataModel
import com.example.data.api.car.repository.CarRepository
import com.example.data.impl.network.car.datasource.CarQueryDataSource
import javax.inject.Inject

class CarRepositoryImpl @Inject constructor(
    private val dataSource: CarQueryDataSource
) : CarRepository {

    override suspend fun getYears(): CarYearsDataModel =
        dataSource.getYears()

    override suspend fun getMakes(year: Int?): List<CarMakeDataModel> =
        dataSource.getMakes(year)

    override suspend fun getModels(
        make: String,
        year: Int?,
        body: String?
    ): List<CarModelDataModel> =
        dataSource.getModels(make, year, body)

    override suspend fun getTrims(
        make: String?,
        model: String?,
        year: Int?,
        body: String?,
        keyword: String?
    ): List<CarTrimDataModel> =
        dataSource.getTrims(make, model, year, body, keyword)

    override suspend fun getModelById(modelId: String): CarModelDetailsDataModel =
        dataSource.getModelById(modelId)
}

