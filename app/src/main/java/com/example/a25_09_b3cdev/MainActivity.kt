package com.example.a25_09_b3cdev

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.a25_09_b3cdev.presentation.ui.AppNavigation
import com.example.a25_09_b3cdev.presentation.ui.theme.A25_09_b3cdevTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            A25_09_b3cdevTheme {
                AppNavigation()
            }
        }
    }
}