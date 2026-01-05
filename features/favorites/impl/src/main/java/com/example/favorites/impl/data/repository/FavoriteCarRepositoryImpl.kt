package com.example.favorites.impl.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.common.utils.Constants
import com.example.data.api.user.AuthService
import com.example.favorites.api.domain.model.FavoriteCarDomainModel
import com.example.favorites.api.domain.repository.FavoriteCarRepository
import com.example.favorites.impl.data.datasource.FavoriteCarDataSource
import com.example.favorites.impl.data.repository.pagingsource.FavoriteCarsPagingSource
import com.example.favorites.impl.data.utils.toFavoriteCarDataModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteCarRepositoryImpl @Inject constructor(
    private val dataSource: FavoriteCarDataSource,
    private val authService: AuthService
) : FavoriteCarRepository {

    override fun getFavoriteCars(): Flow<PagingData<FavoriteCarDomainModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.DEFAULT_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                FavoriteCarsPagingSource(
                    dataSource = dataSource,
                    authService = authService
                )
            }
        ).flow
    }

    override suspend fun addToFavorites(car: FavoriteCarDomainModel) {
        val userId = authService.getCurrentUserId() ?: return
        dataSource.addFavoriteCar(userId, car.toFavoriteCarDataModel())
    }

    override suspend fun removeFromFavorites(carId: String) {
        val userId = authService.getCurrentUserId() ?: return
        dataSource.removeFavoriteCar(userId, carId)
    }

    override suspend fun isFavorite(carId: String): Boolean {
        val userId = authService.getCurrentUserId() ?: return false
        return dataSource.isFavorite(userId, carId)
    }
}

