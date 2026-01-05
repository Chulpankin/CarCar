package com.example.carsearch.impl.presentation.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.carsearch.impl.R
import com.example.carsearch.impl.presentation.CarSearchViewModel
import com.example.carsearch.impl.presentation.model.CarSearchAction
import com.example.carsearch.impl.presentation.model.CarSearchEvent
import com.example.carsearch.impl.presentation.model.CarSearchState
import com.example.data.api.car.model.CarMakeDataModel
import com.example.data.api.car.model.CarModelDataModel
import com.example.data.api.car.model.CarTrimDataModel
import com.example.ui.themes.ButtonTopSpacing
import com.example.ui.themes.TextFieldSpacing
import com.example.ui.view.composable.CarCarScaffold
import com.example.ui.view.composable.DropdownField
import com.example.ui.view.composable.PrimaryButton
import com.example.ui.view.composable.TopBar

@Composable
fun CarSearchScreen(
    viewModel: CarSearchViewModel
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val noDataYearMessage = stringResource(R.string.no_data_year)
    val noDataMakeMessage = stringResource(R.string.no_data_make)
    val noDataModelMessage = stringResource(R.string.no_data_model)
    val noDataTrimMessage = stringResource(R.string.no_data_trim)

    LaunchedEffect(Unit) {
        viewModel.actionsFlow.collect { action ->
            when (action) {
                is CarSearchAction.ShowError ->
                    snackbarHostState.showSnackbar(message = action.message)
                is CarSearchAction.ShowNoDataMessage -> {
                    when (action.fieldType) {
                        "year" -> noDataYearMessage
                        "make" -> noDataMakeMessage
                        "model" -> noDataModelMessage
                        "trim" -> noDataTrimMessage
                        else -> null
                    }?.let { snackbarHostState.showSnackbar(message = it) }
                }
            }
        }
    }

    CarCarScaffold(
        topBar = {
            TopBar(
                title = stringResource(R.string.car_search_title)
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { paddingValues ->
        CarSearchContent(
            uiState = uiState,
            paddingValues = paddingValues,
            onYearFieldClick = { viewModel.obtainEvent(CarSearchEvent.YearFieldClick) },
            onYearSelected = { viewModel.obtainEvent(CarSearchEvent.YearSelected(it)) },
            onYearExpandedChange = {
                viewModel.obtainEvent(CarSearchEvent.YearExpandedChange(it))
            },
            onMakeFieldClick = { viewModel.obtainEvent(CarSearchEvent.MakeFieldClick) },
            onMakeSelected = { viewModel.obtainEvent(CarSearchEvent.MakeSelected(it)) },
            onMakeExpandedChange = {
                viewModel.obtainEvent(CarSearchEvent.MakeExpandedChange(it))
            },
            onModelFieldClick = { viewModel.obtainEvent(CarSearchEvent.ModelFieldClick) },
            onModelSelected = { viewModel.obtainEvent(CarSearchEvent.ModelSelected(it)) },
            onModelExpandedChange = {
                viewModel.obtainEvent(CarSearchEvent.ModelExpandedChange(it))
            },
            onTrimFieldClick = { viewModel.obtainEvent(CarSearchEvent.TrimFieldClick) },
            onTrimSelected = { viewModel.obtainEvent(CarSearchEvent.TrimSelected(it)) },
            onTrimExpandedChange = {
                viewModel.obtainEvent(CarSearchEvent.TrimExpandedChange(it))
            },
            onSearchClick = { viewModel.obtainEvent(CarSearchEvent.SearchClick) }
        )
    }
}

@Composable
private fun CarSearchContent(
    uiState: CarSearchState,
    paddingValues: PaddingValues,
    onYearFieldClick: () -> Unit,
    onYearSelected: (Int) -> Unit,
    onYearExpandedChange: (Boolean) -> Unit,
    onMakeFieldClick: () -> Unit,
    onMakeSelected: (CarMakeDataModel) -> Unit,
    onMakeExpandedChange: (Boolean) -> Unit,
    onModelFieldClick: () -> Unit,
    onModelSelected: (CarModelDataModel) -> Unit,
    onModelExpandedChange: (Boolean) -> Unit,
    onTrimFieldClick: () -> Unit,
    onTrimSelected: (CarTrimDataModel) -> Unit,
    onTrimExpandedChange: (Boolean) -> Unit,
    onSearchClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AnimatedVisibility(
            visible = uiState.selectedYear == null,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            Text(
                text = stringResource(R.string.car_search_description),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        DropdownField(
            onValueChange = onYearSelected,
            label = stringResource(R.string.year_label),
            options = uiState.availableYears,
            isLoading = uiState.isLoadingYears,
            enabled = !uiState.isLoadingYears && (uiState.availableYears.isNotEmpty() || !uiState.hasLoadedYears),
            getDisplayText = { it.toString() },
            displayText = uiState.yearDisplayText,
            modifier = Modifier.fillMaxWidth(),
            expanded = uiState.isYearFieldExpanded,
            onExpandedChange = onYearExpandedChange,
            onFieldClick = onYearFieldClick,
            placeholder = when {
                uiState.hasLoadedYears && uiState.availableYears.isEmpty() -> stringResource(R.string.year_hint_no_data)
                uiState.hasLoadedYears -> stringResource(R.string.year_hint_select)
                else -> stringResource(R.string.year_hint)
            },
            contentDescription = when {
                uiState.hasLoadedYears && uiState.availableYears.isEmpty() -> stringResource(R.string.year_hint_no_data)
                uiState.hasLoadedYears -> stringResource(R.string.year_hint_select)
                else -> stringResource(R.string.year_hint)
            }
        )

        AnimatedVisibility(
            visible = uiState.isMakeFieldVisible,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically()
        ) {
            Spacer(modifier = Modifier.height(TextFieldSpacing))
            DropdownField(
                onValueChange = onMakeSelected,
                label = stringResource(R.string.make),
                options = uiState.availableMakes,
                isLoading = uiState.isLoadingMakes,
                enabled = !uiState.isLoadingMakes && (uiState.availableMakes.isNotEmpty() || !uiState.hasLoadedMakes),
                getDisplayText = { it.makeDisplay },
                displayText = uiState.makeDisplayText,
                modifier = Modifier.fillMaxWidth(),
                expanded = uiState.isMakeFieldExpanded,
                onExpandedChange = onMakeExpandedChange,
                onFieldClick = onMakeFieldClick,
                placeholder = when {
                    uiState.hasLoadedMakes && uiState.availableMakes.isEmpty() ->
                        stringResource(R.string.make_hint_no_data)
                    uiState.hasLoadedMakes -> stringResource(R.string.make_hint_select)
                    else -> stringResource(R.string.make_hint)
                }
            )
        }

        AnimatedVisibility(
            visible = uiState.isModelFieldVisible,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically()
        ) {
            Spacer(modifier = Modifier.height(TextFieldSpacing))
            DropdownField(
                onValueChange = onModelSelected,
                label = stringResource(R.string.model),
                options = uiState.availableModels,
                isLoading = uiState.isLoadingModels,
                enabled = !uiState.isLoadingModels &&
                    (uiState.availableModels.isNotEmpty() || !uiState.hasLoadedModels),
                getDisplayText = { it.modelName },
                displayText = uiState.modelDisplayText,
                modifier = Modifier.fillMaxWidth(),
                expanded = uiState.isModelFieldExpanded,
                onExpandedChange = onModelExpandedChange,
                onFieldClick = onModelFieldClick,
                placeholder = when {
                    uiState.hasLoadedModels && uiState.availableModels.isEmpty() ->
                        stringResource(R.string.model_hint_no_data)
                    uiState.hasLoadedModels -> stringResource(R.string.model_hint_select)
                    else -> stringResource(R.string.model_hint)
                }
            )
        }

        AnimatedVisibility(
            visible = uiState.isTrimFieldVisible,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically()
        ) {
            Spacer(modifier = Modifier.height(TextFieldSpacing))
            DropdownField(
                onValueChange = onTrimSelected,
                label = stringResource(R.string.trim),
                options = uiState.availableTrims,
                isLoading = uiState.isLoadingTrims,
                enabled = !uiState.isLoadingTrims && (uiState.availableTrims.isNotEmpty() || !uiState.hasLoadedTrims),
                getDisplayText = { it.modelTrim.ifEmpty { it.modelName } },
                displayText = uiState.trimDisplayText,
                modifier = Modifier.fillMaxWidth(),
                expanded = uiState.isTrimFieldExpanded,
                onExpandedChange = onTrimExpandedChange,
                onFieldClick = onTrimFieldClick,
                placeholder = when {
                    uiState.hasLoadedTrims && uiState.availableTrims.isEmpty() ->
                        stringResource(R.string.trim_hint_no_data)
                    uiState.hasLoadedTrims -> stringResource(R.string.trim_hint_select)
                    else -> stringResource(R.string.trim_hint)
                }
            )
        }
        Spacer(modifier = Modifier.height(0.dp))

        AnimatedVisibility(
            visible = uiState.isFormValid,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically()
        ) {
            Spacer(modifier = Modifier.height(ButtonTopSpacing))
            PrimaryButton(
                text = stringResource(R.string.search),
                onClick = onSearchClick,
                modifier = Modifier.fillMaxWidth(),
                enabled = uiState.isFormValid
            )
        }
    }
}
