package com.example.carcar.features.carlist

import com.example.carlist.api.navigation.CarListRouter
import com.example.carcar.navigation.GlobalRouter
import javax.inject.Inject

class AdapterCarListRouter @Inject constructor(
    private val globalRouter: GlobalRouter
) : CarListRouter {
    override fun navigateToCarSearch() {
        globalRouter.navigateToCarSearch()
    }

    override fun navigateBack() {
        globalRouter.popBackStack()
    }
}

