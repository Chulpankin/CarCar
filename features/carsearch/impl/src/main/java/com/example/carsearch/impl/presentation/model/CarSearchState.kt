package com.example.carsearch.impl.presentation.model

import com.example.data.api.car.model.CarMakeDataModel
import com.example.data.api.car.model.CarModelDataModel
import com.example.data.api.car.model.CarTrimDataModel

data class CarSearchState(
    val selectedYear: Int? = null,
    val selectedMake: CarMakeDataModel? = null,
    val selectedModel: CarModelDataModel? = null,
    val selectedTrim: CarTrimDataModel? = null,
    val availableYears: List<Int> = emptyList(),
    val availableMakes: List<CarMakeDataModel> = emptyList(),
    val availableModels: List<CarModelDataModel> = emptyList(),
    val availableTrims: List<CarTrimDataModel> = emptyList(),
    val isLoadingYears: Boolean = false,
    val isLoadingMakes: Boolean = false,
    val isLoadingModels: Boolean = false,
    val isLoadingTrims: Boolean = false,
    val hasLoadedYears: Boolean = false,
    val hasLoadedMakes: Boolean = false,
    val hasLoadedModels: Boolean = false,
    val hasLoadedTrims: Boolean = false,
    val isYearFieldExpanded: Boolean = false,
    val isMakeFieldExpanded: Boolean = false,
    val isModelFieldExpanded: Boolean = false,
    val isTrimFieldExpanded: Boolean = false,
    val yearDisplayText: String = "",
    val makeDisplayText: String = "",
    val modelDisplayText: String = "",
    val trimDisplayText: String = "",
    val isMakeFieldVisible: Boolean = false,
    val isModelFieldVisible: Boolean = false,
    val isTrimFieldVisible: Boolean = false
) {
    val isFormValid: Boolean
        get() = selectedYear != null && selectedMake != null && selectedModel != null && selectedTrim != null
}

