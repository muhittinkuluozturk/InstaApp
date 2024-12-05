package com.example.instaapp.base.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import coil.compose.rememberAsyncImagePainter
import com.example.instaapp.base.extension.EMPTY
import com.example.instaapp.ui.home.data.model.MediaItem
import com.example.instagramcloneapp.R

@Composable
fun ImageItem(item: MediaItem) {
    val mediaUrl: String?
    var caption: String? = null
    var timestamp: String? = null

        when (item) {
            is MediaItem.Post -> {
                mediaUrl = item.instPost.media_url
                caption = item.instPost.caption
                timestamp = item.instPost.timestamp
            }
            is MediaItem.Child -> {
                mediaUrl = item.instChild.media_url
            }
        }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_8))
    ) {
        Image(
            painter = rememberAsyncImagePainter(mediaUrl),
            contentDescription = caption,
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.image_height))
                .clip(RoundedCornerShape(dimensionResource(id = R.dimen.padding_8))),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_8)))

        caption?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_4))
            )
        }

        Text(
            text = timestamp?.split("T")?.get(0) ?: EMPTY ,
            style = MaterialTheme.typography.body2,
            color = Color.Gray
        )
    }
}