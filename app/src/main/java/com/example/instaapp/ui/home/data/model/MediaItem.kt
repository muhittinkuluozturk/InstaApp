package com.example.instaapp.ui.home.data.model

/**
 * Farklı medya öğesi türlerini temsil eden sınıf.
 */
sealed class MediaItem {
    data class Post(val instPost: InstPost) : MediaItem()
    data class Child(val instChild: InstChild) : MediaItem()
}