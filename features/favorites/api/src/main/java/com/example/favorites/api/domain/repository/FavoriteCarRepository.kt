package com.example.favorites.api.domain.repository

import androidx.paging.PagingData
import com.example.favorites.api.domain.model.FavoriteCarDomainModel
import kotlinx.coroutines.flow.Flow

interface FavoriteCarRepository {

    fun getFavoriteCars(): Flow<PagingData<FavoriteCarDomainModel>>

    suspend fun addToFavorites(car: FavoriteCarDomainModel)

    suspend fun removeFromFavorites(carId: String)

    suspend fun isFavorite(carId: String): Boolean
}

