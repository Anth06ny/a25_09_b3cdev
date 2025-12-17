package com.example.a25_09_b3cdev.model

import android.annotation.SuppressLint
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.delay
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

object KtorWeatherApi {
    private const val API_URL =
        "https://api.openweathermap.org/data/2.5/find?appid=b80967f0a6bd10d23e44848547b26550&units=metric&lang=fr&q="

    //Création et réglage du client
    private val client = HttpClient {
        install(Logging) {
            //(import io.ktor.client.plugins.logging.Logger)
            logger = object : Logger {
                override fun log(message: String) {
                    println(message)
                }
            }
            level = LogLevel.INFO  // TRACE, HEADERS, BODY, etc.
        }
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true }, contentType = ContentType.Any)
        }
        expectSuccess = true //Exception si code >= 300
        //engine { proxy = ProxyBuilder.http("monproxy:1234") }
    }

    //GET Le JSON reçu sera parser en List<MuseumObject>,
    //Crash si le JSON ne correspond pas
    suspend fun loadWeathers(cityname:String): List<WeatherBean> {
        val res =  client.get(API_URL + cityname) {
//            headers {
//                append("Authorization", "Bearer YOUR_TOKEN")
//                append("Custom-Header", "CustomValue")
//            }
        }.body<WeatherAPIResult>()

        res.list.forEach {w->
            w.weather.forEach {
                it.icon = "https://openweathermap.org/img/wn/${it.icon}@4x.png"
            }
        }

        return res.list

    }

    //Ferme le Client mais celui ci ne sera plus utilisable. Uniquement pour le main
    fun close() = client.close()

}

//DATA CLASS

@SuppressLint("UnsafeOptInUsageError")
@Serializable //KotlinX impose cette annotation
data class WeatherAPIResult(val list:List<WeatherBean>)

@SuppressLint("UnsafeOptInUsageError")
@Serializable //KotlinX impose cette annotation
data class WeatherBean(val name:String, val id : Long, val main  : TempBean, val wind : WindBean, val weather : List<DescriptionBean>)

@SuppressLint("UnsafeOptInUsageError")
@Serializable //KotlinX impose cette annotation
data class DescriptionBean(val description:String, var icon:String)

@SuppressLint("UnsafeOptInUsageError")
@Serializable //KotlinX impose cette annotation
data class TempBean(val temp:Double)

@SuppressLint("UnsafeOptInUsageError")
@Serializable //KotlinX impose cette annotation
data class WindBean(val speed:Double)



