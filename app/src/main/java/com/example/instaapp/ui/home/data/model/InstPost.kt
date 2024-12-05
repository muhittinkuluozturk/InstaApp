package com.example.instaapp.ui.home.data.model

/**
 * Instagram Post için data class
 */
data class InstPost(
    val id: String? = null,
    val caption: String? = null,
    val media_type: String? = null,
    val media_url: String? = null,
    val timestamp: String? = null,
    val children: List<InstChild>? = null
)