package com.example.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.domain.user.AddContactUseCase
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class AddContactViewModel @Inject constructor(
    private val addContactUseCase: AddContactUseCase

    ) : ViewModel() {

    private val _contactAdded = MutableStateFlow<String?>(null)
    val contactAdded: StateFlow<String?> = _contactAdded

    fun addContact(name: String, phone: String, email: String) {
        viewModelScope.launch {
            val userId = FirebaseAuth.getInstance().currentUser?.uid
            if (userId != null) {
                val contactId = addContactUseCase(userId, name, phone, email)
                _contactAdded.value = contactId
            } else {
                _contactAdded.value = null // Manejar caso de error si no hay usuario autenticado
            }
        }
    }
}