package com.example.a25_09_b3cdev.viewmodel

import androidx.lifecycle.ViewModel
import com.example.a25_09_b3cdev.model.KtorWeatherApi
import com.example.a25_09_b3cdev.model.WeatherBean
import kotlinx.coroutines.flow.MutableStateFlow


class MainViewModel : ViewModel() {
    //MutableStateFlow est une donnée observable
    val dataList = MutableStateFlow(emptyList<WeatherBean>())

    suspend fun loadWeathers(cityName: String) {
        //TODO récupérer des données et les mettre dans dataList
        dataList.value = KtorWeatherApi.loadWeathers(cityName)
    }
}