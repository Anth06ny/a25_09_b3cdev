package com.example.a25_09_b3cdev

import com.example.a25_09_b3cdev.data.remote.KtorWeatherApi
import junit.framework.TestCase
import junit.framework.TestCase.assertTrue
import junit.framework.TestCase.fail
import kotlinx.coroutines.runBlocking
import org.junit.Test

class KtorWeatherAPITest {

    @Test
    fun loadWeatherNiceTest() = runBlocking<Unit> {

        val list = KtorWeatherApi.loadWeathers("Nice")

        assertTrue("La liste est vide", list.isNotEmpty())

        list.forEach {
            assertTrue("La ville ne contient pas Nice", it.name.contains("Nice", true))

            assertTrue("La température n'est pas entre -40 et 60", it.main.temp in -40.0..60.0)

            TestCase.assertNotNull("Aucune icone", it.weather.firstOrNull { it.icon.isNotBlank() }  )
        }
    }

    @Test
    fun loadWeathersEmptyString() = runBlocking<Unit> {
        try {
            KtorWeatherApi.loadWeathers("Nice")
            fail("Une exception aurait du être déclenchée")
        }
        catch(e:Exception){}
    }
}