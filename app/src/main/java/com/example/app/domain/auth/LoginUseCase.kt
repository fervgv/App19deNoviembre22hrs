package com.example.app.domain.auth

import com.example.app.data.repository.auth.LoginRepository
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject


class LoginUseCase @Inject constructor(
        private val loginRepository: LoginRepository
    ) {

        suspend operator fun invoke(email: String, password: String): Result<FirebaseUser> {
            return loginRepository.loginWithEmailPassword(email, password)
        }
    }


