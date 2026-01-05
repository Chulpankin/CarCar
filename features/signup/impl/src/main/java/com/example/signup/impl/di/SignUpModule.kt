package com.example.signup.impl.di

import androidx.lifecycle.ViewModel
import com.example.signup.impl.presentation.SignUpViewModel
import com.example.ui.viewmodel.ViewModelKey
import com.example.ui.viewmodel.ViewModelModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [
    ViewModelModule::class,
])
interface SignUpModule {

    @[Binds IntoMap ViewModelKey(SignUpViewModel::class)]
    fun bindSignUpViewModel(signUpViewModel: SignUpViewModel): ViewModel
}

