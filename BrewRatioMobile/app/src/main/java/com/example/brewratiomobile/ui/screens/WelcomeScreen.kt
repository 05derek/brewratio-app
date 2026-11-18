package com.example.brewratiomobile.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.brewratiomobile.R
import com.example.brewratiomobile.ui.components.CoffeeLogo
import com.example.brewratiomobile.ui.theme.BorderSubtle
import com.example.brewratiomobile.ui.theme.ButtonDark
import com.example.brewratiomobile.ui.theme.EspressoDark
import com.example.brewratiomobile.ui.theme.TextMuted
import com.example.brewratiomobile.ui.theme.WarmGold

@Composable
fun WelcomeScreen(
    onNavigateToLogin: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(EspressoDark)
    ) {
        // 1. Gambar Biji Kopi di bagian atas
        Image(
            painter = painterResource(id = R.drawable.bg_cafe),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.55f)
                .align(Alignment.TopCenter),
            contentScale = ContentScale.Crop
        )

        // 2. Gradasi Gelap Halus (Fade dari transparan ke warna background gelap pekat)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
                .align(Alignment.TopCenter)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            EspressoDark.copy(alpha = 0.5f),
                            EspressoDark
                        )
                    )
                )
        )

        // 3. Konten Teks dan Tombol di Bawah
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp, vertical = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            // Logo Utama (Kantong Infus Kopi)
            CoffeeLogo()

            Spacer(modifier = Modifier.height(14.dp))

            // Sub-Brand
            Text(
                text = stringResource(id = R.string.brand_name),
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 3.sp,
                color = WarmGold
            )

            Spacer(modifier = Modifier.height(28.dp))

            // Judul Utama (Find your favorite)
            Text(
                text = stringResource(id = R.string.welcome_headline),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Deskripsi Singkat
            Text(
                text = stringResource(id = R.string.welcome_desc),
                fontSize = 13.sp,
                color = TextMuted,
                textAlign = TextAlign.Center,
                lineHeight = 20.sp,
                modifier = Modifier.padding(horizontal = 12.dp)
            )

            Spacer(modifier = Modifier.height(44.dp))

            // Tombol Pill-Shaped "let's started ->"
            Button(
                onClick = onNavigateToLogin,
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = ButtonDark,
                    contentColor = Color.White
                ),
                border = BorderStroke(1.dp, BorderSubtle),
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .height(52.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.btn_lets_started),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "➔",
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
