package com.example.favorites.impl.presentation

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.favorites.api.domain.repository.FavoriteCarRepository
import com.example.favorites.api.domain.usecase.GetFavoriteCarsUseCase
import com.example.favorites.api.navigation.FavoritesRouter
import com.example.data.api.analytics.datasource.AnalyticsService
import com.example.favorites.api.presentation.utils.toUiModel
import com.example.favorites.impl.presentation.model.FavoritesAction
import com.example.favorites.impl.presentation.model.FavoritesEvent
import com.example.favorites.impl.presentation.model.FavoritesState
import com.example.ui.base.BaseViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(
    private val router: FavoritesRouter,
    private val getFavoriteCarsUseCase: GetFavoriteCarsUseCase,
    private val favoriteCarRepository: FavoriteCarRepository,
    analyticsService: AnalyticsService
) : BaseViewModel<FavoritesState, FavoritesEvent, FavoritesAction>(
    initialState = FavoritesState()
) {

    init {
        analyticsService.logScreenView("favorites", "FavoritesViewModel")
    }

    private fun loadFavoriteCars() {
        _uiState.value = _uiState.value.copy(
            isLoading = false,
            isRefreshing = false,
            carsFlow = getFavoriteCarsUseCase.invoke()
                .cachedIn(viewModelScope)
                .map { it.map { car -> car.toUiModel() } }
        )
    }

    override fun obtainEvent(event: FavoritesEvent) {
        when (event) {
            is FavoritesEvent.Initiate -> loadFavoriteCars()
            is FavoritesEvent.Refresh -> {
                _uiState.value = _uiState.value.copy(
                    isRefreshing = true,
                    isLoading = false,
                    isError = false
                )
                loadFavoriteCars()
            }
            is FavoritesEvent.FavoriteClick -> {
                handleFavoriteClick(event.carId)
            }
            is FavoritesEvent.BackClick -> router.navigateBack()
        }
    }

    private fun handleFavoriteClick(carId: String) {
        _uiState.value = _uiState.value.copy(
            loadingFavoriteCarIds = _uiState.value.loadingFavoriteCarIds + carId
        )
        viewModelScope.launch {
            runCatching {
                favoriteCarRepository.removeFromFavorites(carId)
            }.onSuccess {
                _uiState.value = _uiState.value.copy(
                    loadingFavoriteCarIds = _uiState.value.loadingFavoriteCarIds - carId
                )
            }.onFailure {
                _uiState.value = _uiState.value.copy(
                    loadingFavoriteCarIds = _uiState.value.loadingFavoriteCarIds - carId
                )
            }
        }
    }
}

