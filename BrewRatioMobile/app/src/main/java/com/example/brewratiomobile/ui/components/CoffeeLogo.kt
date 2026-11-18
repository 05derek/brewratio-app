package com.example.brewratiomobile.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.brewratiomobile.R

@Composable
fun CoffeeLogo(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.size(76.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "Logo BrewRatio",
            modifier = Modifier.size(72.dp),
            contentScale = ContentScale.Fit
        )
    }
}
