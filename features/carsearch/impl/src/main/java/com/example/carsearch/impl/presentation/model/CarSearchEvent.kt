package com.example.carsearch.impl.presentation.model

import com.example.data.api.car.model.CarMakeDataModel
import com.example.data.api.car.model.CarModelDataModel
import com.example.data.api.car.model.CarTrimDataModel

sealed interface CarSearchEvent {
    data object YearFieldClick : CarSearchEvent
    data class YearExpandedChange(val expanded: Boolean) : CarSearchEvent
    data class YearSelected(val year: Int) : CarSearchEvent
    data object MakeFieldClick : CarSearchEvent
    data class MakeExpandedChange(val expanded: Boolean) : CarSearchEvent
    data class MakeSelected(val make: CarMakeDataModel) : CarSearchEvent
    data object ModelFieldClick : CarSearchEvent
    data class ModelExpandedChange(val expanded: Boolean) : CarSearchEvent
    data class ModelSelected(val model: CarModelDataModel) : CarSearchEvent
    data object TrimFieldClick : CarSearchEvent
    data class TrimExpandedChange(val expanded: Boolean) : CarSearchEvent
    data class TrimSelected(val trim: CarTrimDataModel) : CarSearchEvent
    data object SearchClick : CarSearchEvent
    data object BackClick : CarSearchEvent
}

