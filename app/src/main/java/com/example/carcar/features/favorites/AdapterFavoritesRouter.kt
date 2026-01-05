package com.example.carcar.features.favorites

import com.example.carcar.navigation.GlobalRouter
import com.example.favorites.api.navigation.FavoritesRouter
import javax.inject.Inject

class AdapterFavoritesRouter @Inject constructor(
    private val globalRouter: GlobalRouter
) : FavoritesRouter {

    override fun navigateBack() {
        globalRouter.popBackStack()
    }
}

