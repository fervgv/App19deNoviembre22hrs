package com.example.app.domain.auth

import com.example.app.data.repository.auth.SignUpRepository
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject
class SignUpUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<FirebaseUser> {
        return signUpRepository.signUpWithEmailPassword(email, password)
    }
}

