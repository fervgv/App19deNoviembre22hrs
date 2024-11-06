package com.example.app.view.login
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.ImeAction
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.app.R
import com.example.app.view.ui.theme.Black
import com.example.app.view.ui.theme.SelectedField
import com.example.app.view.ui.theme.UnselectedField
import com.example.app.viewmodel.LoginViewModel


@Composable
    fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(), // Inyectamos el ViewModel
    navigateToHome: () -> Unit
    ) {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        // Observamos el estado del login desde el ViewModel
        val loginState by viewModel.loginState.collectAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Black)
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row() {
                Icon(
                    painter = painterResource(id = R.drawable.ice_back_24),
                    contentDescription = "",
                    tint = White,
                    modifier = Modifier
                        .padding(vertical = 24.dp)
                        .size(24.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
            }

            Text("Email", color = White, fontWeight = FontWeight.Bold, fontSize = 40.sp)
            TextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = UnselectedField,
                    focusedContainerColor = SelectedField
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { /* Focus the password field */ }
                )
            )

            Spacer(Modifier.height(48.dp))

            Text("Password", color = White, fontWeight = FontWeight.Bold, fontSize = 40.sp)
            TextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = UnselectedField,
                    focusedContainerColor = SelectedField
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { viewModel.login(email, password) }
                )
            )

            Spacer(Modifier.height(48.dp))

            Button(onClick = { viewModel.login(email, password) }) {
                Text(text = "Login")
            }

            Spacer(Modifier.height(16.dp))
            // Muestra el estado del login
            when {
                loginState?.isSuccess == true -> {
                    // Si el login fue realizado  navegar a la siguiente pantalla
                    LaunchedEffect(loginState) {
                        navigateToHome() // Llamar a la función para navegar
                        Log.i("Login", "LOGIN OK")
                    }
                }
                loginState?.isFailure == true -> {
                    // Si hubo un error, mostrar un mensaje de error
                    val exception = loginState!!.exceptionOrNull() // Obtener la excepción
                    Text(
                        text = "Error: ${exception?.message}",
                        color = MaterialTheme.colorScheme.error
                    )
                    Log.i("Login", "LOGIN KO: ${exception?.message}")
                }
                loginState == null -> {
                    // Mientras no haya estado de login definido, podemos mostrar un loading
                    CircularProgressIndicator()
                }
            }
        }
    }
