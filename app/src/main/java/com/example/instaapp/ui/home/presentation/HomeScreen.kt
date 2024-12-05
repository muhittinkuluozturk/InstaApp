package com.example.instaapp.ui.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.instaapp.base.components.CarouselView
import com.example.instaapp.base.components.ImageItem
import com.example.instaapp.base.components.VideoItem
import com.example.instaapp.base.enums.MediaType
import com.example.instaapp.base.extension.EMPTY
import com.example.instaapp.ui.home.data.model.MediaItem
import com.example.instagramcloneapp.R

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {

    val posts by viewModel.posts.observeAsState(initial = emptyList())
    val error by viewModel.error.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.loadUserMedia("1", "accessToken")
    }
    Scaffold(
        topBar = { Toolbar() },
        snackbarHost = { snackbarHostState ->
            error?.let {
                LaunchedEffect(snackbarHostState) {
                    snackbarHostState.showSnackbar(it)
                }
            }
        }
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier.padding(contentPadding)
        ) {
            items(posts) { post ->
                if (posts.isNotEmpty()) {
                    when (post.media_type) {
                        MediaType.IMAGE.name -> ImageItem(MediaItem.Post(post))
                        MediaType.VIDEO.name -> VideoItem(post)
                        MediaType.CAROUSEL_ALBUM.name -> CarouselView(post)
                    }
                }
            }
        }
    }
}


@Composable
fun Toolbar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.padding_56))
            .padding(horizontal = dimensionResource(id = R.dimen.padding_12)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_6)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                ImageVector.vectorResource(id = R.drawable.ic_instagram),
                contentDescription = EMPTY
            )
        }
        Icon(
            ImageBitmap.imageResource(id = R.drawable.ic_dm),
            modifier = Modifier.size(dimensionResource(id = R.dimen.size_24)),
            contentDescription = EMPTY
        )
    }
}
