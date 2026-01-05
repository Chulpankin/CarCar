package com.example.carcar.navigation

interface GlobalRouter {

    fun popBackStack()

    fun navigateToMain()

    fun navigateToSignUp()

    fun navigateToSignIn()

    fun navigateToCarSearch()

    fun navigateToCarList(
        make: String? = null,
        model: String? = null,
        year: Int? = null,
        body: String? = null,
        keyword: String? = null,
    )
}