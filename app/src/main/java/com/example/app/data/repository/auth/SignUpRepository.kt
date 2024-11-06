package com.example.app.data.repository.auth

import kotlinx.coroutines.tasks.await
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject
import javax.inject.Singleton

// SignUpRepository
@Singleton
class SignUpRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) {

    suspend fun signUpWithEmailPassword(email: String, password: String): Result<FirebaseUser> {
        return try {
            val authResult = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            Result.success(authResult.user!!)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }
}
