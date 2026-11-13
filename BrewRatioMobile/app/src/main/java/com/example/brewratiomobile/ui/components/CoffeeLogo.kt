package com.example.brewratiomobile.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.example.brewratiomobile.ui.theme.LightBeige

@Composable
fun CoffeeLogo(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(64.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(LightBeige.copy(alpha = 0.15f)),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(34.dp)) {
            val strokeWidth = 2.5f
            val color = LightBeige

            // Uap kopi artistik
            val steamPath1 = Path().apply {
                moveTo(size.width * 0.35f, size.height * 0.25f)
                quadraticBezierTo(size.width * 0.4f, size.height * 0.15f, size.width * 0.35f, size.height * 0.05f)
            }
            val steamPath2 = Path().apply {
                moveTo(size.width * 0.6f, size.height * 0.28f)
                quadraticBezierTo(size.width * 0.55f, size.height * 0.18f, size.width * 0.65f, size.height * 0.08f)
            }
            drawPath(path = steamPath1, color = color, style = Stroke(width = 2f))
            drawPath(path = steamPath2, color = color, style = Stroke(width = 2f))

            // Bentuk Cangkir Minimalis
            val cupPath = Path().apply {
                moveTo(size.width * 0.2f, size.height * 0.35f)
                lineTo(size.width * 0.7f, size.height * 0.35f)
                quadraticBezierTo(size.width * 0.95f, size.height * 0.35f, size.width * 0.9f, size.height * 0.55f)
                lineTo(size.width * 0.8f, size.height * 0.85f)
                quadraticBezierTo(size.width * 0.75f, size.height * 0.95f, size.width * 0.5f, size.height * 0.95f)
                lineTo(size.width * 0.3f, size.height * 0.95f)
                quadraticBezierTo(size.width * 0.15f, size.height * 0.95f, size.width * 0.2f, size.height * 0.75f)
                close()
            }
            drawPath(path = cupPath, color = color, style = Stroke(width = strokeWidth))
        }
    }
}