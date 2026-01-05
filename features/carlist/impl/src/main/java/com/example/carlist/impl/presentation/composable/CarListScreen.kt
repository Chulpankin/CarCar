package com.example.carlist.impl.presentation.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.carlist.impl.R
import com.example.carlist.impl.presentation.CarListViewModel
import com.example.carlist.impl.presentation.model.CarListEvent
import com.example.carlist.impl.presentation.model.CarListState
import com.example.ui.R as UiR
import com.example.ui.model.CarUiModel
import com.example.ui.themes.Shapes
import com.example.ui.view.composable.CarCarScaffold
import com.example.ui.view.composable.CarListItemSkeleton
import com.example.ui.view.composable.ErrorScreen
import com.example.ui.view.composable.PrimaryButton
import com.example.ui.view.composable.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarListScreen(viewModel: CarListViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val cars = uiState.carsFlow.collectAsLazyPagingItems()

    CarCarScaffold(
        topBar = {
            TopBar(
                title = stringResource(R.string.carlist),
                navigationIcon = {
                    IconButton(onClick = { viewModel.obtainEvent(CarListEvent.BackClick) }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(UiR.string.back)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        PullToRefreshBox(
            modifier = Modifier
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize(),
            isRefreshing = uiState.isRefreshing,
            onRefresh = {
                viewModel.obtainEvent(CarListEvent.Refresh)
                cars.refresh()
            }
        ) {
            if (uiState.isError) {
                ErrorScreen(onRetryClick = { viewModel.obtainEvent(CarListEvent.Refresh) })
            } else if (
                cars.loadState.refresh is LoadState.NotLoading &&
                cars.itemCount == 0
            ) {
                EmptyCarListScreen(
                    onBackClick = { viewModel.obtainEvent(CarListEvent.BackClick) },
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                CarListContent(
                    cars = cars,
                    onCarClick = { viewModel.obtainEvent(CarListEvent.CarClick(it)) },
                    onFavoriteClick = { carId ->
                        val isFavorite = uiState.favoriteCarIds.contains(carId)
                        viewModel.obtainEvent(CarListEvent.FavoriteClick(carId, isFavorite))
                    },
                    isFavorite = { uiState.favoriteCarIds.contains(it) },
                    isLoadingFavorite = { uiState.loadingFavoriteCarIds.contains(it) },
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
private fun CarListContent(
    cars: LazyPagingItems<CarUiModel>,
    onCarClick: (String) -> Unit,
    onFavoriteClick: (String) -> Unit = {},
    isFavorite: (String) -> Boolean = { false },
    isLoadingFavorite: (String) -> Boolean = { false },
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .background(color = MaterialTheme.colorScheme.surfaceContainer)
                .fillMaxWidth(),
        ) {
            carsList(
                cars = cars,
                onCarClick = onCarClick,
                onFavoriteClick = onFavoriteClick,
                isFavorite = isFavorite,
                isLoadingFavorite = isLoadingFavorite
            )
        }
    }
}

private fun LazyListScope.carsList(
    cars: LazyPagingItems<CarUiModel>,
    onCarClick: (String) -> Unit,
    onFavoriteClick: (String) -> Unit = {},
    isFavorite: (String) -> Boolean = { false },
    isLoadingFavorite: (String) -> Boolean = { false }
) {
    when (cars.loadState.refresh) {
        is LoadState.Error -> item {
            ErrorScreen(onRetryClick = { cars.retry() })
        }
        is LoadState.Loading -> {
            items(5) {
                CarListItemSkeleton(modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp))
            }
        }
        is LoadState.NotLoading -> {
            items(cars.itemCount) { index ->
                cars[index]?.let { car ->
                    CarListItem(
                        car = car,
                        onCarClick = onCarClick,
                        isFavorite = isFavorite(car.id),
                        isLoadingFavorite = isLoadingFavorite(car.id),
                        onFavoriteClick = onFavoriteClick,
                        modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp)
                    )
                }
            }
            if (cars.loadState.append is LoadState.Loading) {
                item {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth()
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun CarListItem(
    car: CarUiModel,
    onCarClick: (String) -> Unit,
    isFavorite: Boolean = false,
    isLoadingFavorite: Boolean = false,
    onFavoriteClick: (String) -> Unit = {},
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onCarClick(car.id) },
        shape = Shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.Top
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = stringResource(
                        R.string.car_name,
                        car.makeName.ifEmpty { car.makeId },
                        car.modelName
                    ),
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                if (car.modelTrim.isNotEmpty()) {
                    Text(
                        text = car.modelTrim,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (car.modelYear.isNotEmpty()) {
                        Text(
                            text = stringResource(R.string.year, car.modelYear),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    car.modelBody?.let { body ->
                        Text(
                            text = stringResource(R.string.body_with_bullet, body),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.padding(top = 2.dp)
                ) {
                    car.modelEnginePowerHp?.let { hp ->
                        Text(
                            text = stringResource(R.string.hp_with_value, hp),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    car.modelDrive?.let { drive ->
                        Text(
                            text = stringResource(R.string.drive_with_icon, drive),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                if (car.makeCountry.isNotEmpty() || car.makeId.isNotEmpty() || car.modelId.isNotEmpty()) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(top = 2.dp)
                    ) {
                        if (car.makeCountry.isNotEmpty()) {
                            Text(
                                text = stringResource(R.string.make_country, car.makeCountry),
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                            )
                        }
                        if (car.makeId.isNotEmpty()) {
                            Text(
                                text = stringResource(R.string.make_id, car.makeId),
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                            )
                        }
                        if (car.modelId.isNotEmpty()) {
                            Text(
                                text = stringResource(R.string.model_id, car.modelId),
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                            )
                        }
                    }
                }
            }

            Box(
                modifier = Modifier.align(Alignment.Top),
                contentAlignment = Alignment.Center
            ) {
                IconButton(
                    onClick = { onFavoriteClick(car.id) },
                    enabled = !isLoadingFavorite
                ) {
                    if (isLoadingFavorite) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            strokeWidth = 2.dp
                        )
                    } else {
                        Icon(
                            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = if (isFavorite) {
                                stringResource(R.string.remove_from_favorites)
                            } else {
                                stringResource(R.string.add_to_favorites)
                            },
                            tint = if (isFavorite) {
                                MaterialTheme.colorScheme.error
                            } else {
                                MaterialTheme.colorScheme.onSurfaceVariant
                            }
                        )
                    }
                }
            }
        }
    }
}


@Composable
private fun EmptyCarListScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.no_cars_found),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        Text(
            text = stringResource(R.string.empty_car_list_message),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 32.dp)
        )
        
        PrimaryButton(
            text = stringResource(UiR.string.back),
            onClick = onBackClick,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}
