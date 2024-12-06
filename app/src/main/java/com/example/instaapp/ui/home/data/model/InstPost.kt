package com.example.instaapp.ui.home.data.model

/**
 * Instagram Post için data class
 */
data class InstPost(
    val id: String,
    val caption: String? = null,
    val media_type: String? = null,
    val media_url: String? = null,
    val timestamp: String,
    val children: List<InstChild>? = null,
    val username: String? = null,
    val commentsCount: Int = 0,
    val likesCount: Int = 0,
    val userProfilePicture: String? = null
)