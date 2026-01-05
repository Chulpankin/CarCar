package com.example.carsearch.impl.presentation

import androidx.lifecycle.viewModelScope
import com.example.common.utils.AppExceptionHandler
import com.example.common.utils.runSuspendCatching
import com.example.carsearch.api.navigation.CarSearchRouter
import com.example.carsearch.impl.presentation.model.CarSearchAction
import com.example.carsearch.impl.presentation.model.CarSearchEvent
import com.example.carsearch.impl.presentation.model.CarSearchState
import com.example.data.api.analytics.datasource.AnalyticsService
import com.example.data.api.car.model.CarMakeDataModel
import com.example.data.api.car.model.CarModelDataModel
import com.example.data.api.car.model.CarTrimDataModel
import com.example.data.api.car.usecase.GetCarMakesUseCase
import com.example.data.api.car.usecase.GetCarModelsUseCase
import com.example.data.api.car.usecase.GetCarTrimsUseCase
import com.example.data.api.car.usecase.GetCarYearsUseCase
import com.example.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class CarSearchViewModel @Inject constructor(
    private val router: CarSearchRouter,
    private val getCarYearsUseCase: GetCarYearsUseCase,
    private val getCarMakesUseCase: GetCarMakesUseCase,
    private val getCarModelsUseCase: GetCarModelsUseCase,
    private val getCarTrimsUseCase: GetCarTrimsUseCase,
    private val appExceptionHandler: AppExceptionHandler,
    private val analyticsService: AnalyticsService
) : BaseViewModel<CarSearchState, CarSearchEvent, CarSearchAction>(
        initialState = CarSearchState()
    ) {

    init {
        analyticsService.logScreenView("car_search", "CarSearchViewModel")
    }


    override fun obtainEvent(event: CarSearchEvent) {
        when (event) {
            is CarSearchEvent.YearFieldClick -> loadYears()
            is CarSearchEvent.YearExpandedChange -> onYearExpandedChange(event.expanded)
            is CarSearchEvent.YearSelected -> onYearSelected(event.year)
            is CarSearchEvent.MakeFieldClick -> loadMakes()
            is CarSearchEvent.MakeExpandedChange -> onMakeExpandedChange(event.expanded)
            is CarSearchEvent.MakeSelected -> onMakeSelected(event.make)
            is CarSearchEvent.ModelFieldClick -> loadModels()
            is CarSearchEvent.ModelExpandedChange -> onModelExpandedChange(event.expanded)
            is CarSearchEvent.ModelSelected -> onModelSelected(event.model)
            is CarSearchEvent.TrimFieldClick -> loadTrims()
            is CarSearchEvent.TrimExpandedChange -> onTrimExpandedChange(event.expanded)
            is CarSearchEvent.TrimSelected -> onTrimSelected(event.trim)
            is CarSearchEvent.SearchClick -> onSearchClick()
            is CarSearchEvent.BackClick -> router.navigateBack()
        }
    }

    private fun loadYears() {
        _uiState.value.let { state ->
            if (state.isLoadingYears) return
            _uiState.value = state.copy(
                isLoadingYears = true,
                isYearFieldExpanded = false,
                yearDisplayText = ""
            )
            viewModelScope.launch {
                runSuspendCatching(appExceptionHandler) {
                    getCarYearsUseCase.invoke()
                }.onSuccess { yearsData ->
                    val years = (yearsData.minYear..yearsData.maxYear).reversed().toList()
                    _uiState.value = _uiState.value.copy(
                        availableYears = years,
                        isLoadingYears = false,
                        hasLoadedYears = true,
                        isYearFieldExpanded = years.isNotEmpty(),
                        yearDisplayText = state.selectedYear?.toString().orEmpty()
                    )
                }.onFailure {
                    _uiState.value = _uiState.value.copy(
                        isLoadingYears = false,
                        isYearFieldExpanded = false,
                        yearDisplayText = state.selectedYear?.toString().orEmpty()
                    )
                    _actionsFlow.emit(CarSearchAction.ShowError(it.message.orEmpty()))
                }
            }
        }
    }

    private fun onYearExpandedChange(expanded: Boolean) {
        _uiState.value = _uiState.value.copy(isYearFieldExpanded = expanded)
    }

    private fun onYearSelected(year: Int) {
        _uiState.value = _uiState.value.copy(
            selectedYear = year,
            selectedMake = null,
            selectedModel = null,
            selectedTrim = null,
            availableMakes = emptyList(),
            availableModels = emptyList(),
            availableTrims = emptyList(),
            isLoadingMakes = false,
            isLoadingModels = false,
            isLoadingTrims = false,
            hasLoadedMakes = false,
            hasLoadedModels = false,
            hasLoadedTrims = false,
            isYearFieldExpanded = false,
            isMakeFieldExpanded = false,
            isModelFieldExpanded = false,
            isTrimFieldExpanded = false,
            yearDisplayText = year.toString(),
            makeDisplayText = "",
            modelDisplayText = "",
            trimDisplayText = "",
            isMakeFieldVisible = true,
            isModelFieldVisible = false,
            isTrimFieldVisible = false
        )
    }

    private fun loadMakes() {
        _uiState.value.let { state ->
            if (state.isLoadingMakes) return
            _uiState.value = state.copy(
                isLoadingMakes = true,
                isMakeFieldExpanded = false,
                makeDisplayText = ""
            )
            viewModelScope.launch {
                runSuspendCatching(appExceptionHandler) {
                    getCarMakesUseCase.invoke(null)
                }.onSuccess { makes ->
                    _uiState.value = _uiState.value.copy(
                        availableMakes = makes,
                        isLoadingMakes = false,
                        hasLoadedMakes = true,
                        isMakeFieldExpanded = makes.isNotEmpty(),
                        makeDisplayText = state.selectedMake?.makeDisplay.orEmpty()
                    )
                    if (makes.isEmpty()) {
                        _actionsFlow.emit(CarSearchAction.ShowNoDataMessage("make"))
                    }
                }.onFailure {
                    _uiState.value = _uiState.value.copy(
                        isLoadingMakes = false,
                        isMakeFieldExpanded = false,
                        makeDisplayText = state.selectedMake?.makeDisplay.orEmpty()
                    )
                    _actionsFlow.emit(CarSearchAction.ShowError(it.message.orEmpty()))
                }
            }
        }
    }

    private fun onMakeExpandedChange(expanded: Boolean) {
        _uiState.value = _uiState.value.copy(isMakeFieldExpanded = expanded)
    }

    private fun onMakeSelected(make: CarMakeDataModel) {
        _uiState.value = _uiState.value.copy(
            selectedMake = make,
            selectedModel = null,
            selectedTrim = null,
            availableModels = emptyList(),
            availableTrims = emptyList(),
            isLoadingModels = false,
            isLoadingTrims = false,
            hasLoadedModels = false,
            hasLoadedTrims = false,
            isMakeFieldExpanded = false,
            isModelFieldExpanded = false,
            isTrimFieldExpanded = false,
            makeDisplayText = make.makeDisplay,
            modelDisplayText = "",
            trimDisplayText = "",
            isModelFieldVisible = true,
            isTrimFieldVisible = false
        )
    }

    private fun loadModels() {
        _uiState.value.let { state ->
            if (state.selectedYear == null || state.selectedMake == null) return
            if (state.isLoadingModels) return
            _uiState.value = state.copy(
                isLoadingModels = true,
                isModelFieldExpanded = false,
                modelDisplayText = ""
            )
            viewModelScope.launch {
                runSuspendCatching(appExceptionHandler) {
                    getCarModelsUseCase.invoke(
                        state.selectedMake.makeId,
                        state.selectedYear,
                        null
                    )
                }.onSuccess { models ->
                    _uiState.value = _uiState.value.copy(
                        availableModels = models,
                        isLoadingModels = false,
                        hasLoadedModels = true,
                        isModelFieldExpanded = models.isNotEmpty(),
                        modelDisplayText = state.selectedModel?.modelName.orEmpty()
                    )
                    if (models.isEmpty()) {
                        _actionsFlow.emit(CarSearchAction.ShowNoDataMessage("model"))
                    }
                }.onFailure {
                    _uiState.value = _uiState.value.copy(
                        isLoadingModels = false,
                        isModelFieldExpanded = false,
                        modelDisplayText = state.selectedModel?.modelName.orEmpty()
                    )
                    _actionsFlow.emit(CarSearchAction.ShowError(it.message.orEmpty()))
                }
            }
        }
    }

    private fun onModelExpandedChange(expanded: Boolean) {
        _uiState.value = _uiState.value.copy(isModelFieldExpanded = expanded)
    }

    private fun onModelSelected(model: CarModelDataModel) {
        _uiState.value = _uiState.value.copy(
            selectedModel = model,
            selectedTrim = null,
            availableTrims = emptyList(),
            isLoadingTrims = false,
            hasLoadedTrims = false,
            isModelFieldExpanded = false,
            isTrimFieldExpanded = false,
            modelDisplayText = model.modelName,
            trimDisplayText = "",
            isTrimFieldVisible = true
        )
    }

    private fun loadTrims() {
        _uiState.value.let { state ->
            if (
                state.selectedYear == null ||
                state.selectedMake == null ||
                state.selectedModel == null
            ) return
            if (state.isLoadingTrims) return
            _uiState.value = state.copy(
                isLoadingTrims = true,
                isTrimFieldExpanded = false,
                trimDisplayText = ""
            )
            viewModelScope.launch {
                runSuspendCatching(appExceptionHandler) {
                    getCarTrimsUseCase.invoke(
                        make = state.selectedMake.makeId,
                        model = state.selectedModel.modelName,
                        year = state.selectedYear,
                        body = null,
                        keyword = null
                    )
                }.onSuccess { trims ->
                    val trimText = state.selectedTrim?.let {
                        it.modelTrim.ifEmpty { it.modelName }
                    }.orEmpty()
                    _uiState.value = _uiState.value.copy(
                        availableTrims = trims,
                        isLoadingTrims = false,
                        hasLoadedTrims = true,
                        isTrimFieldExpanded = trims.isNotEmpty(),
                        trimDisplayText = trimText
                    )
                    if (trims.isEmpty()) {
                        _actionsFlow.emit(CarSearchAction.ShowNoDataMessage("trim"))
                    }
                }.onFailure {
                    val trimText = state.selectedTrim?.let {
                        it.modelTrim.ifEmpty { it.modelName }
                    }.orEmpty()
                    _uiState.value = _uiState.value.copy(
                        isLoadingTrims = false,
                        isTrimFieldExpanded = false,
                        trimDisplayText = trimText
                    )
                    _actionsFlow.emit(CarSearchAction.ShowError(it.message.orEmpty()))
                }
            }
        }
    }

    private fun onTrimExpandedChange(expanded: Boolean) {
        _uiState.value = _uiState.value.copy(isTrimFieldExpanded = expanded)
    }

    private fun onTrimSelected(trim: CarTrimDataModel) {
        _uiState.value = _uiState.value.copy(
            selectedTrim = trim,
            isTrimFieldExpanded = false,
            trimDisplayText = trim.modelTrim.ifEmpty { trim.modelName }
        )
    }

    private fun onSearchClick() {
        val state = _uiState.value
        router.navigateToCarList(
            make = state.selectedMake?.makeId,
            model = state.selectedModel?.modelName,
            year = state.selectedYear,
            body = null,
            keyword = null
        )
    }
}

