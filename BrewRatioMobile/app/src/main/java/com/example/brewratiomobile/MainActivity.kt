package com.example.brewratiomobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.brewratiomobile.ui.navigation.AppNavGraph
import com.example.brewratiomobile.ui.theme.BrewRatioMobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BrewRatioMobileTheme {
                val navController = rememberNavController()
                AppNavGraph(navController = navController)
            }
        }
    }
}