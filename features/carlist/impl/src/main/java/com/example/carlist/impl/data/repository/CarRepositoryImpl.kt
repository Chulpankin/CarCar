package com.example.carlist.impl.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.common.utils.Constants
import com.example.data.api.car.repository.CarRepository as DataCarRepository
import com.example.carlist.api.domain.model.CarDomainModel
import com.example.carlist.api.domain.repository.CarRepository
import com.example.carlist.impl.data.repository.pagingsource.CarsPagingSource
import com.example.carlist.impl.data.utils.toDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CarRepositoryImpl @Inject constructor(
    private val carRepository: DataCarRepository,
) : CarRepository {

    override fun getCars(
        make: String?,
        model: String?,
        year: Int?,
        body: String?,
        keyword: String?
    ): Flow<PagingData<CarDomainModel>> = flow {
        val makeCountryMap = runCatching {
            carRepository.getMakes(year).associate { it.makeId to it.makeCountry }
        }.getOrElse { emptyMap() }
        emit(makeCountryMap)
    }.flatMapLatest { countryMap ->
        Pager(
            config = PagingConfig(
                pageSize = Constants.DEFAULT_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                CarsPagingSource(
                    carRepository = carRepository,
                    make = make,
                    model = model,
                    year = year,
                    body = body,
                    keyword = keyword
                )
            }
        ).flow
            .map { pagingData ->
                pagingData.map {
                    it.toDomainModel(countryMap)
                }
            }
    }
}

