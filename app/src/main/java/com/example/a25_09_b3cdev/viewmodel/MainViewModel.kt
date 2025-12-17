package com.example.a25_09_b3cdev.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a25_09_b3cdev.model.KtorWeatherApi
import com.example.a25_09_b3cdev.model.WeatherBean
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

suspend fun main(){
    val viewModel = MainViewModel()
    viewModel.loadWeathers("")

    while(viewModel.runInProgress.value) {
        delay(500)
        println("List : ${viewModel.dataList.value}" )
    }
    //Affichage de la liste (qui doit être remplie) contenue dans la donnée observable
    println("List : ${viewModel.dataList.value}" )
    //Pour que le programme s'arrête, inutile sur Android
    KtorWeatherApi.close()
}
class MainViewModel : ViewModel() {
    //MutableStateFlow est une donnée observable
    val dataList = MutableStateFlow(emptyList<WeatherBean>())
    val runInProgress = MutableStateFlow(false)

    suspend fun loadWeathers(cityName: String ) {
        runInProgress.value = true
        viewModelScope.launch(Dispatchers.IO) {
            dataList.value = KtorWeatherApi.loadWeathers(cityName)
            runInProgress.value = false
        }

    }
}