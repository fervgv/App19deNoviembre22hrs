package com.example.app.data.repository.user

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddContactRepository @Inject constructor(
private val db: FirebaseFirestore // Inyectamos Firestore
) {

    // Agregar contacto de emergencia en Firestore
    suspend fun addContact(userId: String, name: String, phone: String, email: String): String? {
        // Referencia a la colección de contactos dentro del documento de usuario en Firestore
        val contactRef = db.collection("Users").document(userId).collection("Contacts").document()

        val contactData = mapOf(
            "contactId" to contactRef.id,  // Usamos el ID generado automáticamente por Firestore
            "name" to name,
            "phone" to phone,
            "email" to email
        )

        return try {
            // Guardamos el contacto en la subcolección "Contacts"
            contactRef.set(contactData).await()
            contactRef.id  // Devolvemos el ID del contacto generado
        } catch (e: Exception) {
            null  // En caso de error, devolvemos null
        }
    }
}

