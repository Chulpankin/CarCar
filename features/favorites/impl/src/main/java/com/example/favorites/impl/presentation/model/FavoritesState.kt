package com.example.favorites.impl.presentation.model

import androidx.paging.PagingData
import com.example.ui.model.CarUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class FavoritesState(
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val carsFlow: Flow<PagingData<CarUiModel>> = emptyFlow(),
    val isError: Boolean = false,
    val loadingFavoriteCarIds: Set<String> = emptySet()
)

