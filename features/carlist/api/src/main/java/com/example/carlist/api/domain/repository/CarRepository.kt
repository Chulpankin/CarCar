package com.example.carlist.api.domain.repository

import androidx.paging.PagingData
import com.example.carlist.api.domain.model.CarDomainModel
import kotlinx.coroutines.flow.Flow

interface CarRepository {

    fun getCars(
        make: String? = null,
        model: String? = null,
        year: Int? = null,
        body: String? = null,
        keyword: String? = null
    ): Flow<PagingData<CarDomainModel>>
}

