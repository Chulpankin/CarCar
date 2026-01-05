package com.example.signup.impl.di

import com.example.common.di.FeatureScope
import com.example.ui.viewmodel.ViewModelModule
import com.example.ui.viewmodel.ViewModelProviderFactory
import dagger.Component

@FeatureScope
@Component(
    dependencies = [SignUpDeps::class],
    modules = [
        ViewModelModule::class,
        SignUpModule::class,
    ]
)
interface SignUpComponent: SignUpDeps {

    val viewModelFactory: ViewModelProviderFactory

    @Component.Factory
    interface Factory {

        fun create(signUpDeps: SignUpDeps): SignUpComponent
    }
}

