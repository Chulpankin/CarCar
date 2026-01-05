package com.example.carlist.impl.data.repository.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.api.car.model.CarTrimDataModel
import com.example.data.api.car.repository.CarRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class CarsPagingSource(
    private val carRepository: CarRepository,
    private val make: String?,
    private val model: String?,
    private val year: Int?,
    private val body: String?,
    private val keyword: String?
) : PagingSource<Int, CarTrimDataModel>() {

    private var allCars: List<CarTrimDataModel>? = null

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, CarTrimDataModel> {
        return runCatching<LoadResult<Int, CarTrimDataModel>> {
            val page = params.key ?: 0
            val cars = allCars ?: withContext(Dispatchers.IO) {
                if (make != null && model != null) {
                    carRepository.getTrims(make, model, year, body, keyword)
                } else {
                    emptyList()
                }
            }.also { allCars = it }
            if (cars.isEmpty()) LoadResult.Page(emptyList(), null, null)
            else {
                val startIndex = page * params.loadSize
                if (startIndex >= cars.size) {
                    LoadResult.Page(emptyList(), null, null)
                } else {
                    val endIndex = minOf(startIndex + params.loadSize, cars.size)
                    LoadResult.Page(
                        data = cars.subList(startIndex, endIndex),
                        prevKey = page.takeIf { it > 0 }?.minus(1),
                        nextKey = endIndex.takeIf { it < cars.size }?.let { page + 1 }
                    )
                }
            }
        }.getOrElse { e -> LoadResult.Error(e) }
    }

    override fun getRefreshKey(
        state: PagingState<Int, CarTrimDataModel>
    ): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
