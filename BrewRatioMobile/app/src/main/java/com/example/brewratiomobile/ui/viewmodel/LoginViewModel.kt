package com.example.brewratiomobile.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    var isSignInSelected by mutableStateOf(true)
        private set

    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    // State untuk menampung pesan error spesifik per input (Inline Validation)
    var emailError by mutableStateOf<String?>(null)
        private set

    var passwordError by mutableStateOf<String?>(null)
        private set

    var generalError by mutableStateOf<String?>(null)
        private set

    fun onEmailChanged(newEmail: String) {
        email = newEmail
        emailError = null
        generalError = null
    }

    fun onPasswordChanged(newPassword: String) {
        password = newPassword
        passwordError = null
        generalError = null
    }

    fun toggleAuthMode(isSignIn: Boolean) {
        isSignInSelected = isSignIn
        emailError = null
        passwordError = null
        generalError = null
    }

    // Fungsi Validasi Sesuai SOP
    fun validateAndSubmit(
        emptyMsg: String,
        invalidEmailMsg: String,
        invalidPasswordMsg: String,
        onSuccessLogin: (String, String) -> Unit,
        onSuccessRegister: (String, String) -> Unit
    ) {
        var isValid = true

        // 1. Cek Kosong
        if (email.isBlank() || password.isBlank()) {
            generalError = emptyMsg
            isValid = false
        }

        // 2. Validasi Email (Wajib ada karakter '@')
        if (email.isNotBlank() && !email.contains("@")) {
            emailError = invalidEmailMsg
            isValid = false
        }

        // 3. Validasi Password SOP Umum (Minimal 8 karakter, ada huruf & angka)
        val passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d).{8,}$".toRegex()
        if (password.isNotBlank() && !password.matches(passwordRegex)) {
            passwordError = invalidPasswordMsg
            isValid = false
        }

        // Jika semua validasi lolos
        if (isValid) {
            if (isSignInSelected) {
                onSuccessLogin(email, password)
            } else {
                onSuccessRegister(email, password)
            }
        }
    }
}