package com.example.carlist.impl.domain.usecase

import androidx.paging.PagingData
import com.example.carlist.api.domain.model.CarDomainModel
import com.example.carlist.api.domain.repository.CarRepository
import com.example.carlist.api.domain.usecase.GetCarsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCarsUseCaseImpl @Inject constructor(
    private val carRepository: CarRepository
) : GetCarsUseCase {

    override operator fun invoke(
        make: String?,
        model: String?,
        year: Int?,
        body: String?,
        keyword: String?
    ): Flow<PagingData<CarDomainModel>> =
        carRepository.getCars(make, model, year, body, keyword)
}

