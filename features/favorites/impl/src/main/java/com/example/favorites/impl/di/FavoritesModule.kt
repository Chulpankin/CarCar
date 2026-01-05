package com.example.favorites.impl.di

import androidx.lifecycle.ViewModel
import com.example.favorites.impl.presentation.FavoritesViewModel
import com.example.ui.viewmodel.ViewModelKey
import com.example.ui.viewmodel.ViewModelModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [
    ViewModelModule::class,
])
interface FavoritesModule {

    @[Binds IntoMap ViewModelKey(FavoritesViewModel::class)]
    fun bindFavoritesViewModel(favoritesViewModel: FavoritesViewModel): ViewModel
}

