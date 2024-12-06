package com.example.instaapp.ui.detail.data.model

import com.example.instaapp.ui.home.data.model.InstPost

/**
 * Gönderi ve yorum verilerini temsil eden data class
 */
data class InstPostDetail(
    val post: InstPost,
    val comments: List<InstComment>
)