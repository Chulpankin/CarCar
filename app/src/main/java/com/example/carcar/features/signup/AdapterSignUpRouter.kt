package com.example.carcar.features.signup

import com.example.signup.api.navigation.SignUpRouter
import com.example.carcar.navigation.GlobalRouter
import javax.inject.Inject

class AdapterSignUpRouter @Inject constructor(
    private val globalRouter: GlobalRouter
) : SignUpRouter {

    override fun navigateToSignIn() {
        globalRouter.navigateToSignIn()
    }

    override fun navigateToFeed() {
        globalRouter.navigateToMain()
    }
}

