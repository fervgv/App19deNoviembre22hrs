package com.example.app.data.model
import com.google.firebase.firestore.DocumentId

// Define la clase User para representar a los usuarios
data class User(
    @DocumentId
    val userId: String? = null, // ID del documento en Firestore
    val name: String? = null, // Nombre del usuario
    val email: String? = null, // Correo electrónico
    val phone: String? = null, // Teléfono
    val contacts: List<Contact>? = null, // Lista de contactos de emergencia
    val location: Location = Location() // Ubicación actual del usuario
)



