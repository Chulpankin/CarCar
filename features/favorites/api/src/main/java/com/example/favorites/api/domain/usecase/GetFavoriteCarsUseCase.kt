package com.example.favorites.api.domain.usecase

import androidx.paging.PagingData
import com.example.favorites.api.domain.model.FavoriteCarDomainModel
import kotlinx.coroutines.flow.Flow

interface GetFavoriteCarsUseCase {

    operator fun invoke(): Flow<PagingData<FavoriteCarDomainModel>>
}

