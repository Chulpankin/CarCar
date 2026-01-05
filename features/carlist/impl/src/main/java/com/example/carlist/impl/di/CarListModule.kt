package com.example.carlist.impl.di

import androidx.lifecycle.ViewModel
import com.example.carlist.impl.presentation.CarListViewModel
import com.example.ui.viewmodel.ViewModelKey
import com.example.ui.viewmodel.ViewModelModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [
    ViewModelModule::class,
])
interface CarListModule {

    @[Binds IntoMap ViewModelKey(CarListViewModel::class)]
    fun bindCarListViewModel(carListViewModel: CarListViewModel): ViewModel
}

