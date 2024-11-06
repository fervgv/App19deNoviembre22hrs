package com.example.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
//Clase aplication primera en llamarse al inicializar la app
@HiltAndroidApp
class SclApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // No es necesario asignar context manualmente
    }
}