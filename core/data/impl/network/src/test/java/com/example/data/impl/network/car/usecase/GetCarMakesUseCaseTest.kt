package com.example.data.impl.network.car.usecase

import com.example.data.api.car.model.CarMakeDataModel
import com.example.data.api.car.repository.CarRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.Assert.assertEquals

class GetCarMakesUseCaseTest {

    @Test
    fun `invoke returns makes from repository with year`() = runTest {
        val repository = mockk<CarRepository>()
        val year = 2023
        val expectedMakes = listOf(
            CarMakeDataModel(makeId = "make1", makeDisplay = "Make 1", makeIsCommon = "1", makeCountry = "Country 1"),
            CarMakeDataModel(makeId = "make2", makeDisplay = "Make 2", makeIsCommon = "1", makeCountry = "Country 2")
        )
        val useCase = GetCarMakesUseCaseImpl(repository)

        coEvery { repository.getMakes(year) } returns expectedMakes

        val result = useCase.invoke(year)

        assertEquals(expectedMakes, result)
        coVerify(exactly = 1) { repository.getMakes(year) }
    }

    @Test
    fun `invoke returns makes from repository without year`() = runTest {
        val repository = mockk<CarRepository>()
        val expectedMakes = listOf(
            CarMakeDataModel(makeId = "make1", makeDisplay = "Make 1", makeIsCommon = "1", makeCountry = "Country 1")
        )
        val useCase = GetCarMakesUseCaseImpl(repository)

        coEvery { repository.getMakes(null) } returns expectedMakes

        val result = useCase.invoke(null)

        assertEquals(expectedMakes, result)
        coVerify(exactly = 1) { repository.getMakes(null) }
    }
}

