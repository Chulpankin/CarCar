package com.example.carsearch.impl.di

import com.example.common.di.FeatureScope
import com.example.ui.viewmodel.ViewModelProviderFactory
import dagger.Component

@FeatureScope
@Component(
    modules = [CarSearchModule::class],
    dependencies = [CarSearchDeps::class]
)
interface CarSearchComponent : CarSearchDeps {

    val viewModelFactory: ViewModelProviderFactory

    @Component.Factory
    interface Factory {

        fun create(carSearchDeps: CarSearchDeps): CarSearchComponent
    }
}

