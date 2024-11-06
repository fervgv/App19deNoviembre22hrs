package com.example.app.domain.user

import com.example.app.data.repository.user.AddContactRepository
import javax.inject.Inject

class AddContactUseCase @Inject constructor(


private val addContactRepository: AddContactRepository
) {
    suspend operator fun invoke(userId: String, name: String, phone: String, email: String): String? {
        return addContactRepository.addContact(userId, name, phone, email)
    }
}