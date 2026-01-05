package com.example.favorites.impl.domain.usecase

import androidx.paging.PagingData
import com.example.favorites.api.domain.model.FavoriteCarDomainModel
import com.example.favorites.api.domain.repository.FavoriteCarRepository
import com.example.favorites.api.domain.usecase.GetFavoriteCarsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteCarsUseCaseImpl @Inject constructor(
    private val favoriteCarRepository: FavoriteCarRepository
) : GetFavoriteCarsUseCase {

    override operator fun invoke(): Flow<PagingData<FavoriteCarDomainModel>> =
        favoriteCarRepository.getFavoriteCars()
}

