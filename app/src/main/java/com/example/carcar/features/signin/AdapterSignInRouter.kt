package com.example.carcar.features.signin

import com.example.signin.api.navigation.SignInRouter
import com.example.carcar.navigation.GlobalRouter
import javax.inject.Inject

class AdapterSignInRouter @Inject constructor(
    private val globalRouter: GlobalRouter
) : SignInRouter {

    override fun navigateToMain() {
        globalRouter.navigateToMain()
    }

    override fun navigateToSignUp() {
        globalRouter.navigateToSignUp()
    }
}