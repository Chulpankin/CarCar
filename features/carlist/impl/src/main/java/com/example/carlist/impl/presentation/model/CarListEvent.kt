package com.example.carlist.impl.presentation.model

sealed interface CarListEvent {
    data class Initiate(
        val make: String? = null,
        val model: String? = null,
        val year: Int? = null,
        val body: String? = null,
        val keyword: String? = null
    ) : CarListEvent
    data class CarClick(val id: String) : CarListEvent
    data class FavoriteClick(val carId: String, val isFavorite: Boolean) : CarListEvent
    data object Refresh : CarListEvent
    data object SearchClick : CarListEvent
    data object BackClick : CarListEvent
}

