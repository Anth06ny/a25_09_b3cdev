package com.example.a25_09_b3cdev

import com.example.a25_09_b3cdev.data.remote.DescriptionEntity
import com.example.a25_09_b3cdev.data.remote.KtorWeatherApi
import com.example.a25_09_b3cdev.data.remote.TempEntity
import com.example.a25_09_b3cdev.data.remote.WeatherEntity
import com.example.a25_09_b3cdev.data.remote.WindEntity
import com.example.a25_09_b3cdev.presentation.viewmodel.MainViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockkObject
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

class MainViewModelTest {

    //Pour piloter les coroutines
    private val testDispatcher = StandardTestDispatcher()


    @Test
    fun loadWeathersNetwork() = runBlocking {

        val viewModel = MainViewModel()


        // Immédiatement après appel
        assertEquals(false, viewModel.runInProgress.value)

        // Appeler la méthode à tester
        val job = viewModel.loadWeathers("Paris")

        assertEquals(true, viewModel.runInProgress.value)

        //Attendre la fin des coroutines
        job.join()

        assertEquals(false, viewModel.runInProgress.value)

        assertTrue("La liste est vide", viewModel.dataList.value.isNotEmpty())
        TestCase.assertNotNull("Aucun élément contient Paris", viewModel.dataList.value.firstOrNull { it.name.contains("Paris", true) })

    }

    @Test
    fun loadWeathersMock() = runTest(testDispatcher) {

        mockkObject(KtorWeatherApi)
        //programmation
        coEvery { KtorWeatherApi.loadWeathers("Paris") }.returns(getParisFakeResult())

        val viewModel = MainViewModel(testDispatcher)

        // Immédiatement après appel
        assertEquals(false, viewModel.runInProgress.value)

        // Appeler la méthode à tester
        viewModel.loadWeathers("Paris")

        assertEquals(true, viewModel.runInProgress.value)

        //déclencher toutes les coroutines
        advanceUntilIdle()

        assertEquals(false, viewModel.runInProgress.value)

        //Mock
        //TODO Tester qu'un seul appel à KtorWeatherAPI.loadWeather("Paris") a été fait
        coVerify { KtorWeatherApi.loadWeathers("Paris") }

        //TODO Tester qu'aucun autre appel à KtorWeatherAPI a été fait
        confirmVerified(KtorWeatherApi)

        //TODO Tester que le titre et l'id du 1er élément sont bien ceux retournés par getParisFakeResult()

        assertEquals("Paris", viewModel.dataList.value.first().name)
        assertEquals(1, viewModel.dataList.value.first().id)

    }

    fun getParisFakeResult() = arrayListOf(
        WeatherEntity(
            id = 1,
            name = "Paris",
            main = TempEntity(temp = 20.0),
            wind = WindEntity(speed = 5.0),
            weather = listOf(DescriptionEntity(description = "Ensoleillé", icon = "01d"))
        )
    )
}