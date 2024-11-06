package com.example.app.data.repository.user

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RegistryContactRepository @Inject constructor(
    private val db: FirebaseFirestore // Inyectamos Firestore
) {

    // Registrar usuario en Firestore
    suspend fun registerUser(userId: String, name: String, email: String, phone: String): Boolean {
        val userRef = db.collection("Users").document(userId)
        val userData = mapOf(
            "userId" to userId,
            "name" to name,
            "email" to email,
            "phone" to phone
        )
        return try {
            // Guardamos el documento del usuario en la colección "Users"
            userRef.set(userData).await()
            true
        } catch (e: Exception) {
            false
        }
    }
}
