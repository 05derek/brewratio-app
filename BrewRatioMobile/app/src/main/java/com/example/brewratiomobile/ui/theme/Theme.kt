package com.example.brewratiomobile.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

// Menggunakan skema warna kustom BrewRatio
private val BrewRatioColorScheme = lightColorScheme(
    primary = DarkForestGreen,
    secondary = CopperBrown,
    background = LightBeige,
    surface = DarkForestGreen,
    onPrimary = LightBeige,
    onSecondary = LightBeige,
    onBackground = DarkForestGreen,
    onSurface = LightBeige,
)

@Composable
fun BrewRatioMobileTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color dimatikan agar palet kustom kita tidak tertimpa warna bawaan HP
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = BrewRatioColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}