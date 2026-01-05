package com.example.carlist.impl.presentation.model

sealed interface CarListAction {
    data object Initiate : CarListAction
}

