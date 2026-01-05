package com.example.carsearch.impl.di

import androidx.lifecycle.ViewModel
import com.example.carsearch.impl.presentation.CarSearchViewModel
import com.example.ui.viewmodel.ViewModelKey
import com.example.ui.viewmodel.ViewModelModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [
    ViewModelModule::class,
])
interface CarSearchModule {

    @[Binds IntoMap ViewModelKey(CarSearchViewModel::class)]
    fun bindCarSearchViewModel(carSearchViewModel: CarSearchViewModel): ViewModel
}

