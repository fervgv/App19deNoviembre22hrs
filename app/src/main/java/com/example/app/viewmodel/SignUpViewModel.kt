package com.example.app.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.domain.auth.SignUpUseCase
import com.example.app.domain.user.RegisterUserUseCase
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val registerUserUseCase: RegisterUserUseCase
) : ViewModel() {

    private val _signUpState = MutableStateFlow<Result<FirebaseUser>?>(null)
    val signUpState: StateFlow<Result<FirebaseUser>?>get() = _signUpState
    private val _userId = MutableStateFlow<String?>(null)
    val userId: StateFlow<String?> = _userId


    fun signUpAndRegisterUser(name: String, email: String, password: String, phone: String) {
        viewModelScope.launch {
            // Crear el usuario en Firebase Authentication
            val result = signUpUseCase(email, password)
            _signUpState.value = result

            if (result.isSuccess) {
                val firebaseUser = result.getOrNull()!!
                val userId = firebaseUser.uid

                // Registrar datos adicionales en Firestore
                val registrationResult = registerUserUseCase.execute(userId, name, email, phone)
                if (registrationResult) {
                    _userId.value = userId
                    Log.i("Registro", "Usuario registrado correctamente en Firestore.")
                } else {
                    Log.e("Registro", "Error al registrar datos en Firestore.")
                }
            } else {
                Log.e("Registro", "Error en Firebase Authentication: ${result.exceptionOrNull()?.message}")
            }
        }
    }

}



