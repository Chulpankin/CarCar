package com.example.carsearch.api.navigation

interface CarSearchRouter {

    fun navigateBack()

    fun navigateToCarList(
        make: String? = null,
        model: String? = null,
        year: Int? = null,
        body: String? = null,
        keyword: String? = null
    )
}

