package com.example.favorites.impl.presentation.model

sealed interface FavoritesEvent {
    data object Initiate : FavoritesEvent
    data object Refresh : FavoritesEvent
    data class FavoriteClick(val carId: String) : FavoritesEvent
    data object BackClick : FavoritesEvent
}

