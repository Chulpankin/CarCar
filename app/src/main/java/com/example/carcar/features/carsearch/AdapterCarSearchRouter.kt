package com.example.carcar.features.carsearch

import com.example.carcar.navigation.GlobalRouter
import com.example.carsearch.api.navigation.CarSearchRouter
import javax.inject.Inject

class AdapterCarSearchRouter @Inject constructor(
    private val globalRouter: GlobalRouter
) : CarSearchRouter {

    override fun navigateBack() {
        globalRouter.popBackStack()
    }

    override fun navigateToCarList(
        make: String?,
        model: String?,
        year: Int?,
        body: String?,
        keyword: String?
    ) {
        globalRouter.navigateToCarList(make, model, year, body)
    }
}

