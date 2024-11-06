package com.example.app.data.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId
//tabla valor
// Define la clase Notification para representar las notificaciones enviadas
data class EmergencyRequest(
    @DocumentId
    val requestId: String? = null, // ID de la solicitud de emergencia
    val userId: String? = null, // ID del usuario que envía la solicitud
    val timestamp: Timestamp = Timestamp.now(), // Fecha y hora de la solicitud
    val location: String, // Ubicación en el momento de la solicitud
    val contactId: Map<String, Boolean>? = null,// ID de contactos notificados y su estado
    val message: String) //temporal debo cambiar este tipo de dato

