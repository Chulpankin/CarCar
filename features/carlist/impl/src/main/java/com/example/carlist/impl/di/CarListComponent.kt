package com.example.carlist.impl.di

import com.example.common.di.FeatureScope
import com.example.ui.viewmodel.ViewModelProviderFactory
import dagger.Component

@FeatureScope
@Component(
    modules = [CarListModule::class],
    dependencies = [CarListDeps::class]
)
interface CarListComponent : CarListDeps {

    val viewModelFactory: ViewModelProviderFactory

    @Component.Factory
    interface Factory {

        fun create(carListDeps: CarListDeps): CarListComponent
    }
}

