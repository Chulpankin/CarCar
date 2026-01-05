package com.example.carlist.impl.domain.usecase

import androidx.paging.PagingData
import com.example.carlist.api.domain.model.CarDomainModel
import com.example.carlist.api.domain.repository.CarRepository
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import org.junit.Test
import org.junit.Assert.assertNotNull

class GetCarsUseCaseTest {

    @Test
    fun `invoke returns flow from repository with all parameters`() = runTest {
        val repository = mockk<CarRepository>()
        val expectedFlow = flowOf(PagingData.empty<CarDomainModel>())
        val useCase = GetCarsUseCaseImpl(repository)
        val make = "make123"
        val model = "model123"
        val year = 2023
        val body = "Sedan"
        val keyword = "test"

        coEvery { repository.getCars(make, model, year, body, keyword) } returns expectedFlow

        val result = useCase.invoke(make, model, year, body, keyword)

        assertNotNull(result)
        assertNotNull(result.first())
        coVerify(exactly = 1) { repository.getCars(make, model, year, body, keyword) }
    }

    @Test
    fun `invoke returns flow from repository with null parameters`() = runTest {
        val repository = mockk<CarRepository>()
        val expectedFlow = flowOf(PagingData.empty<CarDomainModel>())
        val useCase = GetCarsUseCaseImpl(repository)

        coEvery { repository.getCars(null, null, null, null, null) } returns expectedFlow

        val result = useCase.invoke(null, null, null, null, null)

        assertNotNull(result)
        assertNotNull(result.first())
        coVerify(exactly = 1) { repository.getCars(null, null, null, null, null) }
    }
}

