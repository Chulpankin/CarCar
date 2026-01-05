package com.example.data.impl.network.car.usecase

import com.example.data.api.car.model.CarYearsDataModel
import com.example.data.api.car.repository.CarRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.Assert.assertEquals

class GetCarYearsUseCaseTest {

    @Test
    fun `invoke returns years from repository`() = runTest {
        val repository = mockk<CarRepository>()
        val expectedYears = CarYearsDataModel(minYear = 2020, maxYear = 2023)
        val useCase = GetCarYearsUseCaseImpl(repository)

        coEvery { repository.getYears() } returns expectedYears

        val result = useCase.invoke()

        assertEquals(expectedYears, result)
        coVerify(exactly = 1) { repository.getYears() }
    }
}

