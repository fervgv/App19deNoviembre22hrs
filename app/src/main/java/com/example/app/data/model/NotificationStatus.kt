package com.example.app.data.model

data class NotificationStatus(
    val notified: Boolean = false, // indica si se notificó o no
    val error: String? = null // mensaje de error en caso de fallo

)

