package com.example.favorites.impl.di

import com.example.common.di.FeatureScope
import com.example.ui.viewmodel.ViewModelProviderFactory
import dagger.Component

@FeatureScope
@Component(
    modules = [FavoritesModule::class],
    dependencies = [FavoritesDeps::class]
)
interface FavoritesComponent : FavoritesDeps {

    val viewModelFactory: ViewModelProviderFactory

    @Component.Factory
    interface Factory {

        fun create(favoritesDeps: FavoritesDeps): FavoritesComponent
    }
}

