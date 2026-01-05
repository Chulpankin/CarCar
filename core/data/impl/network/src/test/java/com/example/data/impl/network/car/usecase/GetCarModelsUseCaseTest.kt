package com.example.data.impl.network.car.usecase

import com.example.data.api.car.model.CarModelDataModel
import com.example.data.api.car.repository.CarRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.Assert.assertEquals

class GetCarModelsUseCaseTest {

    @Test
    fun `invoke returns models from repository with all parameters`() = runTest {
        val repository = mockk<CarRepository>()
        val make = "make123"
        val year = 2023
        val body = "Sedan"
        val expectedModels = listOf(
            CarModelDataModel(modelName = "Model 1", modelMakeId = "make123"),
            CarModelDataModel(modelName = "Model 2", modelMakeId = "make123")
        )
        val useCase = GetCarModelsUseCaseImpl(repository)

        coEvery { repository.getModels(make, year, body) } returns expectedModels

        val result = useCase.invoke(make, year, body)

        assertEquals(expectedModels, result)
        coVerify(exactly = 1) { repository.getModels(make, year, body) }
    }

    @Test
    fun `invoke returns models from repository with null parameters`() = runTest {
        val repository = mockk<CarRepository>()
        val make = "make123"
        val expectedModels = listOf(
            CarModelDataModel(modelName = "Model 1", modelMakeId = "make123")
        )
        val useCase = GetCarModelsUseCaseImpl(repository)

        coEvery { repository.getModels(make, null, null) } returns expectedModels

        val result = useCase.invoke(make, null, null)

        assertEquals(expectedModels, result)
        coVerify(exactly = 1) { repository.getModels(make, null, null) }
    }
}


