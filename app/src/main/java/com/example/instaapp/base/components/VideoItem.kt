package com.example.instaapp.base.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.instaapp.base.extension.TimeFormater
import com.example.instaapp.ui.home.data.model.InstPost
import com.example.instagramcloneapp.R

@Composable
fun VideoItem(
    post: InstPost,
    onClick: () -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_8))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick)
                .aspectRatio(16f / 9f)
        ) {
            val exoPlayer = ExoPlayer.Builder(context).build().apply {
                val mediaItem = MediaItem.fromUri(post.media_url.toString())
                setMediaItem(mediaItem)
                prepare()
                playWhenReady = true
            }

            AndroidView(
                factory = {
                    PlayerView(context).apply {
                        player = exoPlayer
                        useController = false
                    }
                },
                modifier = Modifier.fillMaxSize()
            )

            DisposableEffect(key1 = exoPlayer) {
                onDispose {
                    exoPlayer.release()
                }
            }
        }

        post.caption?.let { caption ->
            Text(
                text = caption,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_8))
            )
        }

        Text(
            text = TimeFormater.formatTimestamp(post.timestamp),
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_8))
        )

    }
}
