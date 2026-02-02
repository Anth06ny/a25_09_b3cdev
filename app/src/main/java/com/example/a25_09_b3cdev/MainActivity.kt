package com.example.a25_09_b3cdev

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.a25_09_b3cdev.presentation.ui.screens.SearchScreen
import com.example.a25_09_b3cdev.presentation.ui.theme.A25_09_b3cdevTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            A25_09_b3cdevTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SearchScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
data class Dice(var value: Int = 6) {
    fun roll() {
        value = (1..6).random()
    }
}

@Composable
fun Experience(modifier :Modifier = Modifier) {

    Column(modifier) {

        val diceList = remember { mutableStateOf(listOf(Dice(), Dice(), Dice())) }

        Row {
            diceList.value.forEach { Text(text = it.value.toString() + " ") } }

        Button(onClick = {
            diceList.value.forEach { it.roll() }
            println(diceList.value)
        }) { Text(text = "forEach") }

        Button(onClick = {
            diceList.value.forEach { it.roll() }
            diceList.value = diceList.value.toList()
            println(diceList.value)
        }) { Text(text = "forEach + toList") }

        Button(onClick = {
            diceList.value = diceList.value.map { it.roll(); it.copy() }
            println(diceList.value)
        }) { Text(text = "map (roll + copy)") }

        Button(onClick = {
            diceList.value = diceList.value.map { it.copy().apply { roll() } }
            println(diceList.value)
        }) { Text(text = "map (copy + roll)") }
    }
}