package com.example.favorites.impl.domain.usecase

import com.example.favorites.api.domain.repository.FavoriteCarRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue

class IsFavoriteUseCaseTest {

    @Test
    fun `invoke returns true when car is favorite`() = runTest {
        val repository = mockk<FavoriteCarRepository>()
        val carId = "car123"
        val useCase = IsFavoriteUseCaseImpl(repository)

        coEvery { repository.isFavorite(carId) } returns true

        val result = useCase.invoke(carId)

        assertTrue(result)
        coVerify(exactly = 1) { repository.isFavorite(carId) }
    }

    @Test
    fun `invoke returns false when car is not favorite`() = runTest {
        val repository = mockk<FavoriteCarRepository>()
        val carId = "car123"
        val useCase = IsFavoriteUseCaseImpl(repository)

        coEvery { repository.isFavorite(carId) } returns false

        val result = useCase.invoke(carId)

        assertFalse(result)
        coVerify(exactly = 1) { repository.isFavorite(carId) }
    }
}

