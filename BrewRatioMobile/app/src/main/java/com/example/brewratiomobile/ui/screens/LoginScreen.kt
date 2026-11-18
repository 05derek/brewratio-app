package com.example.brewratiomobile.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.brewratiomobile.R
import com.example.brewratiomobile.ui.components.CoffeeLogo
import com.example.brewratiomobile.ui.theme.BorderSubtle
import com.example.brewratiomobile.ui.theme.ButtonDark
import com.example.brewratiomobile.ui.theme.ErrorPastel
import com.example.brewratiomobile.ui.theme.EspressoDark
import com.example.brewratiomobile.ui.theme.SurfaceDark
import com.example.brewratiomobile.ui.theme.TextMuted
import com.example.brewratiomobile.ui.theme.WarmGold
import com.example.brewratiomobile.ui.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    onNavigateToHome: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = viewModel()
) {
    val emptyFieldsError = stringResource(id = R.string.error_empty_fields)
    val invalidEmailError = stringResource(id = R.string.error_invalid_email)
    val invalidPasswordError = stringResource(id = R.string.error_invalid_password)

    var passwordVisible by remember { mutableStateOf(false) }

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
                .fillMaxHeight(0.45f)
                .align(Alignment.TopCenter),
            contentScale = ContentScale.Crop
        )

        // 2. Gradasi Gelap Halus
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
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

        // 3. Form Input & Tombol Aksi
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 28.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(110.dp))

            // Logo Cangkir/Infus Kopi
            CoffeeLogo()

            Spacer(modifier = Modifier.height(10.dp))

            // Brand Tag
            Text(
                text = stringResource(id = R.string.brand_name),
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 2.5.sp,
                color = WarmGold
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Judul Layar: "Sign in" atau "Sign up"
            Text(
                text = if (viewModel.isSignInSelected) {
                    stringResource(id = R.string.login_title)
                } else {
                    stringResource(id = R.string.signup_title)
                },
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Subjudul
            Text(
                text = if (viewModel.isSignInSelected) {
                    stringResource(id = R.string.login_subtitle)
                } else {
                    stringResource(id = R.string.signup_subtitle)
                },
                fontSize = 12.sp,
                color = TextMuted
            )

            Spacer(modifier = Modifier.height(30.dp))

            // --- INPUT EMAIL ---
            AuthInputField(
                label = stringResource(id = R.string.label_email_caps),
                value = viewModel.email,
                onValueChange = { viewModel.onEmailChanged(it) },
                placeholder = stringResource(id = R.string.hint_email),
                keyboardType = KeyboardType.Email,
                enabled = !viewModel.isLoading,
                errorMessage = viewModel.emailError
            )

            Spacer(modifier = Modifier.height(18.dp))

            // --- INPUT PASSWORD ---
            AuthInputField(
                label = stringResource(id = R.string.label_password_caps),
                value = viewModel.password,
                onValueChange = { viewModel.onPasswordChanged(it) },
                placeholder = stringResource(id = R.string.hint_password),
                keyboardType = KeyboardType.Password,
                isPassword = true,
                passwordVisible = passwordVisible,
                onTogglePasswordVisibility = { passwordVisible = !passwordVisible },
                enabled = !viewModel.isLoading,
                errorMessage = viewModel.passwordError
            )

            // Opsi Lupa Password
            if (viewModel.isSignInSelected) {
                Spacer(modifier = Modifier.height(6.dp))
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Text(
                        text = stringResource(id = R.string.forgot_password),
                        color = TextMuted,
                        fontSize = 11.sp,
                        modifier = Modifier
                            .clickable(enabled = !viewModel.isLoading) { }
                            .padding(vertical = 4.dp)
                    )
                }
            } else {
                Spacer(modifier = Modifier.height(14.dp))
            }

            // Pesan Error Umum (Jika Kolom Kosong)
            if (viewModel.generalError != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = viewModel.generalError!!,
                    color = ErrorPastel,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            // --- TOMBOL PILL "let's started ->" ---
            Button(
                onClick = {
                    viewModel.validateAndSubmit(
                        emptyMsg = emptyFieldsError,
                        invalidEmailMsg = invalidEmailError,
                        invalidPasswordMsg = invalidPasswordError,
                        onSuccessLogin = { _, _ -> onNavigateToHome() },
                        onSuccessRegister = { _, _ -> onNavigateToHome() }
                    )
                },
                enabled = !viewModel.isLoading,
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = ButtonDark,
                    contentColor = Color.White,
                    disabledContainerColor = ButtonDark.copy(alpha = 0.5f),
                    disabledContentColor = Color.White.copy(alpha = 0.5f)
                ),
                border = BorderStroke(1.dp, BorderSubtle),
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .height(52.dp)
            ) {
                if (viewModel.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(22.dp),
                        color = WarmGold,
                        strokeWidth = 2.dp
                    )
                } else {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = if (viewModel.isSignInSelected) {
                                stringResource(id = R.string.btn_lets_started)
                            } else {
                                stringResource(id = R.string.btn_signup)
                            },
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
            }

            Spacer(modifier = Modifier.height(24.dp))

            // --- TOGGLE SIGN IN / SIGN UP ---
            Row(
                modifier = Modifier.padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (viewModel.isSignInSelected) {
                        stringResource(id = R.string.prompt_no_account)
                    } else {
                        stringResource(id = R.string.prompt_have_account)
                    },
                    color = TextMuted,
                    fontSize = 12.sp
                )
                Text(
                    text = if (viewModel.isSignInSelected) {
                        stringResource(id = R.string.action_signup)
                    } else {
                        stringResource(id = R.string.action_signin)
                    },
                    color = WarmGold,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable(enabled = !viewModel.isLoading) {
                        viewModel.toggleAuthMode(!viewModel.isSignInSelected)
                    }
                )
            }
        }
    }
}

/**
 * Komponen Input Teks Modular untuk Clean Code & Reusability
 */
@Composable
private fun AuthInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardType: KeyboardType,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
    passwordVisible: Boolean = false,
    onTogglePasswordVisibility: (() -> Unit)? = null,
    enabled: Boolean = true,
    errorMessage: String? = null
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp,
            color = TextMuted,
            modifier = Modifier.padding(bottom = 6.dp, start = 4.dp)
        )

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder, color = Color(0xFF6B6661), fontSize = 14.sp) },
            singleLine = true,
            enabled = enabled,
            isError = errorMessage != null,
            visualTransformation = if (isPassword && !passwordVisible) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            trailingIcon = if (isPassword && onTogglePasswordVisibility != null) {
                {
                    Text(
                        text = if (passwordVisible) {
                            stringResource(id = R.string.hide_password)
                        } else {
                            stringResource(id = R.string.show_password)
                        },
                        color = WarmGold,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .padding(end = 12.dp)
                            .clickable { onTogglePasswordVisibility() }
                    )
                }
            } else null,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = SurfaceDark,
                unfocusedContainerColor = SurfaceDark,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                disabledTextColor = Color.Gray,
                focusedBorderColor = if (errorMessage != null) ErrorPastel else WarmGold,
                unfocusedBorderColor = if (errorMessage != null) ErrorPastel else BorderSubtle,
                cursorColor = WarmGold
            )
        )

        if (errorMessage != null) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "• $errorMessage",
                color = ErrorPastel,
                fontSize = 11.sp,
                modifier = Modifier.padding(start = 6.dp)
            )
        }
    }
}
