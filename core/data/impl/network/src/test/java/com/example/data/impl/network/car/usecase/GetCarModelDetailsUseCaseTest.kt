package com.example.data.impl.network.car.usecase

import com.example.data.api.car.model.CarModelDetailsDataModel
import com.example.data.api.car.repository.CarRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.Assert.assertEquals

class GetCarModelDetailsUseCaseTest {

    @Test
    fun `invoke returns model details from repository`() = runTest {
        val repository = mockk<CarRepository>()
        val modelId = "model123"
        val expectedDetails = CarModelDetailsDataModel(
            modelId = modelId,
            modelMakeId = "make123",
            modelName = "Test Model",
            modelYear = "2023"
        )
        val useCase = GetCarModelDetailsUseCaseImpl(repository)

        coEvery { repository.getModelById(modelId) } returns expectedDetails

        val result = useCase.invoke(modelId)

        assertEquals(expectedDetails, result)
        coVerify(exactly = 1) { repository.getModelById(modelId) }
    }
}

