package com.example.instaapp.ui.detail.data.model

/**
 * Yorum verilerini temsil eden data class
 */
data class InstComment (
    val id: String,
    val text: String,
    val username: String,
    val timestamp: String,
    val userProfilePicture: String
)