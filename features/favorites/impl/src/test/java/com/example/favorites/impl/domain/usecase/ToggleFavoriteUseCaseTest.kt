package com.example.favorites.impl.domain.usecase

import com.example.carlist.api.domain.model.CarDomainModel
import com.example.favorites.api.domain.model.FavoriteCarDomainModel
import com.example.favorites.api.domain.repository.FavoriteCarRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue

class ToggleFavoriteUseCaseTest {

    @Test
    fun `invoke removes from favorites when isFavorite is true`() = runTest {
        val repository = mockk<FavoriteCarRepository>(relaxed = true)
        val useCase = ToggleFavoriteUseCaseImpl(repository)
        val car = createTestCar()
        val isFavorite = true

        coEvery { repository.removeFromFavorites(any()) } returns Unit

        useCase.invoke(car, isFavorite)

        coVerify(exactly = 1) { repository.removeFromFavorites(car.id) }
        coVerify(exactly = 0) { repository.addToFavorites(any()) }
    }

    @Test
    fun `invoke adds to favorites when isFavorite is false`() = runTest {
        val repository = mockk<FavoriteCarRepository>(relaxed = true)
        val useCase = ToggleFavoriteUseCaseImpl(repository)
        val car = createTestCar()
        val isFavorite = false

        coEvery { repository.addToFavorites(any()) } returns Unit

        useCase.invoke(car, isFavorite)

        coVerify(exactly = 1) { repository.addToFavorites(any()) }
        coVerify(exactly = 0) { repository.removeFromFavorites(any()) }
    }

    @Test
    fun `invoke creates correct FavoriteCarDomainModel when adding`() = runTest {
        val repository = mockk<FavoriteCarRepository>(relaxed = true)
        val useCase = ToggleFavoriteUseCaseImpl(repository)
        val car = createTestCar()
        val isFavorite = false

        val slot = slot<FavoriteCarDomainModel>()
        coEvery { repository.addToFavorites(any()) } answers {
            slot.captured = firstArg()
        }

        useCase.invoke(car, isFavorite)

        coVerify(exactly = 1) { repository.addToFavorites(any()) }
        val favoriteCar = slot.captured
        assertEquals(car.id, favoriteCar.id)
        assertEquals(car.modelId, favoriteCar.modelId)
        assertEquals(car.makeId, favoriteCar.makeId)
        assertEquals(car.makeName, favoriteCar.makeName)
        assertEquals(car.makeCountry, favoriteCar.makeCountry)
        assertEquals(car.modelName, favoriteCar.modelName)
        assertEquals(car.modelTrim, favoriteCar.modelTrim)
        assertEquals(car.modelYear, favoriteCar.modelYear)
        assertEquals(car.modelBody, favoriteCar.modelBody)
        assertEquals(car.modelEnginePowerHp, favoriteCar.modelEnginePowerHp)
        assertEquals(car.modelDrive, favoriteCar.modelDrive)
        assertTrue(favoriteCar.isFavorite)
    }

    private fun createTestCar() = CarDomainModel(
        id = "car123",
        modelId = "model123",
        makeId = "make123",
        makeName = "Test Make",
        makeCountry = "Test Country",
        modelName = "Test Model",
        modelTrim = "Test Trim",
        modelYear = "2023",
        modelBody = "Sedan",
        modelEnginePowerHp = "200",
        modelDrive = "FWD"
    )

}


