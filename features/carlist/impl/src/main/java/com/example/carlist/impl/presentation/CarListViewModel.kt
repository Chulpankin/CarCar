package com.example.carlist.impl.presentation

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.carlist.api.domain.usecase.GetCarsUseCase
import com.example.carlist.api.navigation.CarListRouter
import com.example.carlist.impl.presentation.model.CarListAction
import com.example.carlist.impl.presentation.model.CarListEvent
import com.example.carlist.impl.presentation.model.CarListState
import com.example.carlist.api.presentation.utils.toUiModel
import com.example.data.api.analytics.datasource.AnalyticsService
import com.example.favorites.api.domain.usecase.IsFavoriteUseCase
import com.example.favorites.api.domain.usecase.ToggleFavoriteUseCase
import com.example.ui.base.BaseViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class CarListViewModel @Inject constructor(
    private val router: CarListRouter,
    private val getCarsUseCase: GetCarsUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    private val isFavoriteUseCase: IsFavoriteUseCase,
    private val analyticsService: AnalyticsService
) : BaseViewModel<CarListState, CarListEvent, CarListAction>(
    initialState = CarListState()
) {

    init {
        analyticsService.logScreenView("car_list", "CarListViewModel")
    }
    private var make: String? = null
    private var model: String? = null
    private var year: Int? = null
    private var body: String? = null
    private var keyword: String? = null
    private val carsMap = mutableMapOf<String, com.example.carlist.api.domain.model.CarDomainModel>()

    private fun loadCars() {
        _uiState.value = _uiState.value.copy(
            isLoading = false,
            isRefreshing = false,
            carsFlow = getCarsUseCase.invoke(make, model, year, body, keyword)
                .cachedIn(viewModelScope)
                .map { pagingData ->
                    pagingData.map { car ->
                        carsMap[car.id] = car
                        car.toUiModel()
                    }
                }
        )
    }

    override fun obtainEvent(event: CarListEvent) {
        when (event) {
            is CarListEvent.Initiate -> {
                make = event.make
                model = event.model
                year = event.year
                body = event.body
                keyword = event.keyword
                loadCars()
                loadFavoriteStates()
            }
            is CarListEvent.Refresh -> {
                _uiState.value = _uiState.value.copy(
                    isRefreshing = true,
                    isLoading = false,
                    isError = false
                )
                loadCars()
                loadFavoriteStates()
            }
            is CarListEvent.CarClick -> {}
            is CarListEvent.FavoriteClick -> {
                handleFavoriteClick(event.carId, event.isFavorite)
            }
            is CarListEvent.SearchClick -> {
                router.navigateToCarSearch()
            }
            is CarListEvent.BackClick -> {
                router.navigateBack()
            }
        }
    }

    private fun loadFavoriteStates() {
        viewModelScope.launch {
            carsMap.keys.map { carId ->
                carId to isFavoriteUseCase.invoke(carId)
            }.forEach { (carId, isFavorite) ->
                _uiState.value = _uiState.value.copy(
                    favoriteCarIds = if (isFavorite) {
                        _uiState.value.favoriteCarIds + carId
                    } else {
                        _uiState.value.favoriteCarIds - carId
                    }
                )
            }
        }
    }

    private fun handleFavoriteClick(carId: String, isFavorite: Boolean) {
        val car = carsMap[carId] ?: return
        _uiState.value = _uiState.value.copy(
            loadingFavoriteCarIds = _uiState.value.loadingFavoriteCarIds + carId
        )
        viewModelScope.launch {
            runCatching {
                toggleFavoriteUseCase.invoke(car, isFavorite)
            }.onSuccess {
                _uiState.value = _uiState.value.copy(
                    favoriteCarIds = if (isFavorite) {
                        _uiState.value.favoriteCarIds - carId
                    } else {
                        _uiState.value.favoriteCarIds + carId
                    },
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

