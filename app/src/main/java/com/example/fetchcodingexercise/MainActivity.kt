package com.example.fetchcodingexercise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.fetchcodingexercise.ui.screens.FetchHireApp
import com.example.fetchcodingexercise.ui.theme.FetchCodingExerciseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FetchCodingExerciseTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    // Start the Fetch Hire App compose.
                    FetchHireApp()
                }
            }
        }
    }
}