package com.example.instaapp.base.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.instaapp.base.enums.MediaType
import com.example.instaapp.ui.home.data.model.InstPost
import com.example.instaapp.ui.home.data.model.MediaItem
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CarouselView(
    post: InstPost
) {
    val items = post.children ?: emptyList()

    HorizontalPager(
        count = items.size,
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) { pageIndex ->
        val media = items[pageIndex]
        when (media.media_type) {
            MediaType.IMAGE.name -> {
                ImageItem(MediaItem.Child(media))
            }
            MediaType.VIDEO.name -> {
                VideoItem(post)
            }
        }
    }
}