package com.example.app.presentation.signup
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
import com.example.app.R
import com.example.app.ui.theme.Black
import com.example.app.ui.theme.SelectedField
import com.example.app.ui.theme.UnselectedField
import com.google.firebase.auth.FirebaseAuth

@Composable
fun SignUpScreen(auth: FirebaseAuth) {
    var nombre by remember { mutableStateOf("")}
    var email by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var confcontrasena by remember { mutableStateOf("")}
    var telefono by remember { mutableStateOf("")}



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
            value = nombre,
            onValueChange = { nombre = it },
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
            value = contrasena, onValueChange = { contrasena = it },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = UnselectedField,
                focusedContainerColor = SelectedField
            )
        )
        Spacer(Modifier.height(48.dp))
        Text("Confirmar contrasena ", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        TextField(
            value = confcontrasena, onValueChange = { confcontrasena = it },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = UnselectedField,
                focusedContainerColor = SelectedField
            )
        )
        Spacer(Modifier.height(48.dp))
        Text("Nummero de telefono ", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        TextField(
            value = telefono, onValueChange = { telefono = it },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = UnselectedField,
                focusedContainerColor = SelectedField
            )
        )
        Spacer(Modifier.height(48.dp))

        Button(onClick = {
            auth.createUserWithEmailAndPassword(email, contrasena).addOnCompleteListener { task ->
                if(task.isSuccessful){
                    //Registrado
                    Log.i("aris", "Registro OK")
                }else{
                    //Error
                    Log.i("aris", "Registro KO")
                }
            }
        }) {
            Text(text = "Sign Up")
        }
    }
}