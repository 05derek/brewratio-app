package com.example.brewratiomobile

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.brewratiomobile.ui.screens.LoginScreen
import com.example.brewratiomobile.ui.theme.BrewRatioMobileTheme
import com.example.brewratiomobile.ui.viewmodel.LoginViewModel

class MainActivity : ComponentActivity() {
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BrewRatioMobileTheme {
                LoginScreen(
                    viewModel = loginViewModel,
                    onLoginSuccess = { email, _ ->
                        Toast.makeText(this, "Login Berhasil: $email", Toast.LENGTH_SHORT).show()
                    },
                    onRegisterSubmit = { email, _ ->
                        Toast.makeText(this, "Registrasi Berhasil: $email", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }
    }
}