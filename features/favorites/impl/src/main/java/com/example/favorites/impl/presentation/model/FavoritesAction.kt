package com.example.favorites.impl.presentation.model

sealed interface FavoritesAction {
    data object Initiate : FavoritesAction
}

