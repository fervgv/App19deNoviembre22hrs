package com.example.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.domain.auth.LoginUseCase
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase // Inyectamos el caso de uso
) : ViewModel() {

    // MutableStateFlow para manejar el estado de login
    private val _loginState = MutableStateFlow<Result<FirebaseUser>?>(null)
    val loginState: StateFlow<Result<FirebaseUser>?> get() = _loginState

    // Función que invoca el LoginUseCase para realizar el login
    fun login(email: String, password: String) {
        viewModelScope.launch {
            // Llamamos al caso de uso para ejecutar la lógica del login
            val result = loginUseCase(email, password)
            _loginState.value = result // Actualizamos el estado del login
        }
    }
}