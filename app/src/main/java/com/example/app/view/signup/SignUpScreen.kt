package com.example.app.view.signup
import com.example.app.viewmodel.SignUpViewModel
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
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.app.R
import com.example.app.view.ui.theme.Black
import com.example.app.view.ui.theme.SelectedField
import com.example.app.view.ui.theme.UnselectedField

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(), // Inyectando el ViewModel
    navigateToHome: () -> Unit
) {

    var name by remember { mutableStateOf("")}
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confpassword by remember { mutableStateOf("")}
    var phone by remember { mutableStateOf("")}
    val userId = viewModel.userId.collectAsState()




    // Observar el estado del registro
    val signUpState by viewModel.signUpState.collectAsState()


    // Detectar cuando el registro es exitoso para navegar
    signUpState?.let { result ->
        if (result.isSuccess) {
            navigateToHome()
        } else if (result.isFailure) {
            Log.i("Registro", "Registro fallido: ${result.exceptionOrNull()?.message}")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(){
            Icon(
                painter = painterResource(id = R.drawable.ice_back_24),
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .size(12.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
        }
        Text("Nombre", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        TextField(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = UnselectedField,
                focusedContainerColor = SelectedField
            )
        )
        Spacer(Modifier.height(48.dp))

        Text("Email", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        TextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = UnselectedField,
                focusedContainerColor = SelectedField
            )
        )
        Spacer(Modifier.height(48.dp))


        Text("Contraseña", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        TextField(
            value = password, onValueChange = { password = it },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = UnselectedField,
                focusedContainerColor = SelectedField
            )
        )
        Spacer(Modifier.height(48.dp))
        Text("Confirmar contrasena ", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        TextField(
            value = confpassword, onValueChange = { confpassword = it },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = UnselectedField,
                focusedContainerColor = SelectedField
            )
        )
        Spacer(Modifier.height(48.dp))
        Text("Numero de telefono ", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        TextField(
            value = phone, onValueChange = { phone = it },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = UnselectedField,
                focusedContainerColor = SelectedField
            )
        )
        Spacer(Modifier.height(48.dp))

        Button(onClick = {
                viewModel.signUpAndRegisterUser(name, email, password, phone)
            }) {
                Text(text = "Sign Up")
            }
        }
    }


