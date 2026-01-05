package com.example.favorites.impl.domain.usecase

import androidx.paging.PagingData
import com.example.favorites.api.domain.model.FavoriteCarDomainModel
import com.example.favorites.api.domain.repository.FavoriteCarRepository
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import org.junit.Test
import org.junit.Assert.assertNotNull

class GetFavoriteCarsUseCaseTest {

    @Test
    fun `invoke returns flow from repository`() = runTest {
        val repository = mockk<FavoriteCarRepository>()
        val expectedFlow = flowOf(PagingData.empty<FavoriteCarDomainModel>())
        val useCase = GetFavoriteCarsUseCaseImpl(repository)

        coEvery { repository.getFavoriteCars() } returns expectedFlow

        val result = useCase.invoke()

        assertNotNull(result)
        assertNotNull(result.first())
        coVerify(exactly = 1) { repository.getFavoriteCars() }
    }
}

