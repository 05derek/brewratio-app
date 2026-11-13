package com.example.brewratiomobile.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.brewratiomobile.R
import com.example.brewratiomobile.ui.components.CoffeeLogo
import com.example.brewratiomobile.ui.theme.CopperBrown
import com.example.brewratiomobile.ui.theme.LightBeige
import com.example.brewratiomobile.ui.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onLoginSuccess: (String, String) -> Unit,
    onRegisterSubmit: (String, String) -> Unit
) {
    val emptyFieldsError = stringResource(id = R.string.error_empty_fields)
    val invalidEmailError = stringResource(id = R.string.error_invalid_email)
    val invalidPasswordError = stringResource(id = R.string.error_invalid_password)

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg_cafe),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Card(
            modifier = Modifier
                .fillMaxWidth(0.88f)
                .wrapContentHeight()
                .border(
                    width = dimensionResource(id = R.dimen.border_width_thin),
                    color = LightBeige.copy(alpha = 0.3f),
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.card_corner_radius))
                ),
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.card_corner_radius)),
            colors = CardDefaults.cardColors(
                containerColor = CopperBrown.copy(alpha = 0.88f)
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.card_padding_horizontal),
                        vertical = dimensionResource(id = R.dimen.card_padding_vertical)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CoffeeLogo()

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_medium)))

                Text(
                    text = stringResource(id = R.string.app_name),
                    fontSize = dimensionResource(id = R.dimen.text_size_app_title).value.sp,
                    fontWeight = FontWeight.Bold,
                    color = LightBeige
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_xlarge)))

                AuthTabRow(
                    isSignInSelected = viewModel.isSignInSelected,
                    onTabSelected = { viewModel.toggleAuthMode(it) }
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_xxlarge)))

                AuthTextField(
                    label = stringResource(id = R.string.label_email),
                    value = viewModel.email,
                    onValueChange = { viewModel.onEmailChanged(it) },
                    placeholder = stringResource(id = R.string.hint_email),
                    keyboardType = KeyboardType.Email,
                    isError = viewModel.emailError != null,
                    errorMessage = viewModel.emailError
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_large)))

                AuthTextField(
                    label = stringResource(id = R.string.label_password),
                    value = viewModel.password,
                    onValueChange = { viewModel.onPasswordChanged(it) },
                    placeholder = stringResource(id = R.string.hint_password),
                    isPassword = true,
                    keyboardType = KeyboardType.Password,
                    isError = viewModel.passwordError != null,
                    errorMessage = viewModel.passwordError
                )

                if (viewModel.isSignInSelected) {
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_small)))
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                        TextButton(onClick = { }) {
                            Text(
                                text = stringResource(id = R.string.forgot_password),
                                color = LightBeige.copy(alpha = 0.75f),
                                fontSize = 12.sp
                            )
                        }
                    }
                } else {
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_large)))
                }

                if (viewModel.generalError != null) {
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = viewModel.generalError!!,
                        color = Color(0xFFFFCDD2),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_medium)))

                Button(
                    onClick = {
                        viewModel.validateAndSubmit(
                            emptyMsg = emptyFieldsError,
                            invalidEmailMsg = invalidEmailError,
                            invalidPasswordMsg = invalidPasswordError,
                            onSuccessLogin = onLoginSuccess,
                            onSuccessRegister = onRegisterSubmit
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dimensionResource(id = R.dimen.button_height)),
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.button_corner_radius)),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LightBeige,
                        contentColor = CopperBrown
                    )
                ) {
                    Text(
                        text = if (viewModel.isSignInSelected) stringResource(id = R.string.btn_signin) else stringResource(id = R.string.btn_signup),
                        fontWeight = FontWeight.Bold,
                        fontSize = dimensionResource(id = R.dimen.text_size_button).value.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun AuthTabRow(isSignInSelected: Boolean, onTabSelected: (Boolean) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            TextButton(onClick = { onTabSelected(true) }) {
                Text(
                    text = stringResource(id = R.string.tab_signin),
                    color = if (isSignInSelected) LightBeige else LightBeige.copy(alpha = 0.5f),
                    fontWeight = FontWeight.Bold,
                    fontSize = dimensionResource(id = R.dimen.text_size_tab).value.sp
                )
            }
            if (isSignInSelected) {
                Box(modifier = Modifier.width(60.dp).height(2.dp).background(LightBeige))
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            TextButton(onClick = { onTabSelected(false) }) {
                Text(
                    text = stringResource(id = R.string.tab_signup),
                    color = if (!isSignInSelected) LightBeige else LightBeige.copy(alpha = 0.5f),
                    fontWeight = FontWeight.Bold,
                    fontSize = dimensionResource(id = R.dimen.text_size_tab).value.sp
                )
            }
            if (!isSignInSelected) {
                Box(modifier = Modifier.width(60.dp).height(2.dp).background(LightBeige))
            }
        }
    }
}

@Composable
private fun AuthTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isPassword: Boolean = false,
    keyboardType: KeyboardType,
    isError: Boolean = false,
    errorMessage: String? = null
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            color = LightBeige.copy(alpha = 0.95f),
            fontSize = dimensionResource(id = R.dimen.text_size_label).value.sp,
            modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.spacing_small)),
            textAlign = TextAlign.Start
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder, color = LightBeige.copy(alpha = 0.4f)) },
            singleLine = true,
            isError = isError,
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.textfield_corner_radius)),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = LightBeige,
                unfocusedTextColor = LightBeige,
                focusedBorderColor = if (isError) Color(0xFFE57373) else LightBeige,
                unfocusedBorderColor = if (isError) Color(0xFFE57373).copy(alpha = 0.7f) else LightBeige.copy(alpha = 0.4f),
                errorBorderColor = Color(0xFFE57373),
                cursorColor = LightBeige
            )
        )

        if (isError && errorMessage != null) {
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "• $errorMessage",
                    color = Color(0xFFFFCDD2),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}