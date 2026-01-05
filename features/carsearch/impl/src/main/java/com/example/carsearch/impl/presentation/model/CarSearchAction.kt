package com.example.carsearch.impl.presentation.model

sealed interface CarSearchAction {
    data class ShowError(val message: String) : CarSearchAction
    data class ShowNoDataMessage(val fieldType: String) : CarSearchAction
}

