package com.example.a25_09_b3cdev

import com.example.a25_09_b3cdev.model.KtorMuseumApi
import com.example.a25_09_b3cdev.model.KtorUserApi
import com.example.a25_09_b3cdev.model.MuseumObject
import kotlinx.coroutines.runBlocking
import org.junit.Test

class TestAPI {

    @Test
    fun testKtorMuseumAPI() = runBlocking {
        val list : List<MuseumObject> = KtorMuseumApi.getData()
        println(list.joinToString(separator = "\n\n"))
        KtorMuseumApi.close()
    }


    @Test
    fun testKtorUserAPI() = runBlocking {
        val user = KtorUserApi.getRandomUser()
        repeat(5) {
            println(
                """
        Il s'appelle ${user.name} pour le contacter :
        Phone : ${user.coord?.phone ?: "-"}
        Mail : ${user.coord?.mail ?: "-"}
    """.trimIndent()
            )

            println(user)
        }
    }
}