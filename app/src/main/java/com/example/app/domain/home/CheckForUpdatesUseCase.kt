package com.example.app.domain.home

import com.example.app.data.repository.home.HomeRepository
import javax.inject.Inject

class CheckForUpdatesUseCase @Inject constructor(
    private val repository: HomeRepository
) {
    operator fun invoke(): Boolean {
        // Aquí puedes añadir la lógica de verificación, por ejemplo, consultando una API
        return repository.checkForUpdateAvailability()
    }
}