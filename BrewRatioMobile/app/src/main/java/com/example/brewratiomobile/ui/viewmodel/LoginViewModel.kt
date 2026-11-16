package com.example.brewratiomobile.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

    // [BARU] State untuk mengontrol indikator loading pada tombol dan mencegah double-click
    var isLoading by mutableStateOf(false)
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
        // Cegah perpindahan tab saat proses loading sedang berjalan
        if (isLoading) return

        isSignInSelected = isSignIn
        emailError = null
        passwordError = null
        generalError = null
    }

    // Fungsi Validasi Sesuai SOP + Simulasi Loading sebelum Transisi Layar
    fun validateAndSubmit(
        emptyMsg: String,
        invalidEmailMsg: String,
        invalidPasswordMsg: String,
        onSuccessLogin: (String, String) -> Unit,
        onSuccessRegister: (String, String) -> Unit
    ) {
        // Abaikan klik jika proses autentikasi masih berjalan
        if (isLoading) return

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

        // Jika semua validasi lolos, jalankan proses loading sebelum berpindah layar
        if (isValid) {
            viewModelScope.launch {
                isLoading = true

                // Simulasi jeda jaringan/autentikasi agar animasi loading terlihat halus.
                // Nantinya baris delay() ini tinggal diganti dengan pemanggilan API dari folder backend.
                delay(800)

                isLoading = false

                if (isSignInSelected) {
                    onSuccessLogin(email, password)
                } else {
                    onSuccessRegister(email, password)
                }
            }
        }
    }
}