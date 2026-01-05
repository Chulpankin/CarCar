package com.example.favorites.impl.data.repository.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.api.user.AuthService
import com.example.favorites.api.domain.model.FavoriteCarDomainModel
import com.example.favorites.impl.data.datasource.FavoriteCarDataSource
import com.example.favorites.impl.data.utils.toDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavoriteCarsPagingSource(
    private val dataSource: FavoriteCarDataSource,
    private val authService: AuthService
) : PagingSource<String, FavoriteCarDomainModel>() {

    override suspend fun load(
        params: LoadParams<String>
    ): LoadResult<String, FavoriteCarDomainModel> = runCatching {
        val userId = authService.getCurrentUserId()
            ?: return LoadResult.Error(IllegalStateException("User not authenticated"))
        withContext(Dispatchers.IO) {
            dataSource.getFavoriteCars(
                userId = userId,
                pageSize = params.loadSize,
                lastDocumentId = params.key
            )
        }.let { (cars, nextKey) ->
            LoadResult.Page(
                data = cars.map { it.toDomainModel() },
                prevKey = null,
                nextKey = nextKey
            )
        }
    }.getOrElse { LoadResult.Error(it) }

    override fun getRefreshKey(
        state: PagingState<String, FavoriteCarDomainModel>
    ): String? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
                ?: state.closestPageToPosition(anchorPosition)?.nextKey
        }
    }
}

