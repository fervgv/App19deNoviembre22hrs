package com.example.app.domain.user

import com.example.app.data.repository.user.RegistryContactRepository

import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val registryContactRepository: RegistryContactRepository
) {
    suspend fun execute(userId: String, name: String, email: String, phone: String): Boolean {
        return registryContactRepository.registerUser(userId, name, email, phone)
    }
}