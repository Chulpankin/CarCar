package com.example.carcar.di.dependencies

import com.example.carsearch.impl.di.CarSearchDeps
import com.example.signin.impl.di.SignInDeps
import com.example.signup.impl.di.SignUpDeps
import com.example.carlist.impl.di.CarListDeps
import com.example.favorites.impl.di.FavoritesDeps

interface FeatureComponentsDeps :
    SignInDeps,
    SignUpDeps,
    CarListDeps,
    CarSearchDeps,
    FavoritesDeps
