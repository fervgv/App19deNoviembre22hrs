package com.example.app.data.repository.home

import javax.inject.Inject

class HomeRepository @Inject constructor() {
    fun checkForUpdateAvailability(): Boolean {
        // Lógica para verificar si hay una actualización disponible
        return true // Simulación de una actualización disponible
    }
}