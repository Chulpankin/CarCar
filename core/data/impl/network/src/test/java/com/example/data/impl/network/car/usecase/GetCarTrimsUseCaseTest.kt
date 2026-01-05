package com.example.data.impl.network.car.usecase

import com.example.data.api.car.model.CarTrimDataModel
import com.example.data.api.car.repository.CarRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.Assert.assertEquals

class GetCarTrimsUseCaseTest {

    @Test
    fun `invoke returns trims from repository with all parameters`() = runTest {
        val repository = mockk<CarRepository>()
        val make = "make123"
        val model = "model123"
        val year = 2023
        val body = "Sedan"
        val keyword = "test"
        val expectedTrims = listOf(
            CarTrimDataModel(
                modelId = "trim1",
                modelMakeId = "make123",
                modelName = "Trim 1",
                modelTrim = "Base",
                modelYear = "2023"
            )
        )
        val useCase = GetCarTrimsUseCaseImpl(repository)

        coEvery { repository.getTrims(make, model, year, body, keyword) } returns expectedTrims

        val result = useCase.invoke(make, model, year, body, keyword)

        assertEquals(expectedTrims, result)
        coVerify(exactly = 1) { repository.getTrims(make, model, year, body, keyword) }
    }

    @Test
    fun `invoke returns trims from repository with null parameters`() = runTest {
        val repository = mockk<CarRepository>()
        val expectedTrims = emptyList<CarTrimDataModel>()
        val useCase = GetCarTrimsUseCaseImpl(repository)

        coEvery { repository.getTrims(null, null, null, null, null) } returns expectedTrims

        val result = useCase.invoke(null, null, null, null, null)

        assertEquals(expectedTrims, result)
        coVerify(exactly = 1) { repository.getTrims(null, null, null, null, null) }
    }
}

