package com.example.instaapp.data.repository

import com.example.instaapp.data.network.InstApiService
import com.example.instaapp.ui.home.data.model.InstChild
import com.example.instaapp.ui.home.data.model.InstPost
import javax.inject.Inject

class InstRepositoryImpl @Inject constructor(
    private val apiService: InstApiService
) : InstRepository {
    override suspend fun getUserMedia(userId: String, accessToken: String): List<InstPost> {
        return apiService.getUserMedia(userId, accessToken)
    }

    //TODO Mock data denemeleri için eklendi.
    override suspend fun getUserMediaMock(userId: String, accessToken: String): List<InstPost> {
        return listOf(
            InstPost(
                "1",
                "Caption 1",
                "IMAGE",
                "https://www.solosophie.com/wp-content/uploads/2017/08/IMG_20170821_235329_766-819x1024.jpg"
            ),
            InstPost(
                "2",
                "Caption 1",
                "IMAGE",
                "https://www.hlimg.com/images/stories/738X538/instagram-caption-post_1721823115-7202e.jpg"
            ),
            InstPost(
                "3",
                "Caption 1",
                "IMAGE",
                "https://st3.depositphotos.com/3591429/16051/i/450/depositphotos_160518654-stock-photo-hand-holding-mobile-phone.jpg"
            ),
            InstPost(
                "4",
                "Caption 2",
                "VIDEO",
                "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4"
            ),
            InstPost(
                "5",
                "Caption 1",
                "IMAGE",
                "https://i.pinimg.com/736x/c3/af/77/c3af7794b4b167868bc1c882aeb90218.jpg"
            ),
            InstPost(
                "6",
                "Caption 1",
                "CAROUSEL_ALBUM",
                "https://i.pinimg.com/736x/c3/af/77/c3af7794b4b167868bc1c882aeb90218.jpg",
                children = listOf(
                    InstChild(
                        "1",
                        "IMAGE",
                        "https://hips.hearstapps.com/hmg-prod/images/cat-captions-for-instagram-6595a058e956b.jpeg?crop=0.588xw:1.00xh;0.207xw,0&resize=1200:*"
                    ),
                    InstChild(
                        "1",
                        "IMAGE",
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT50notvqBCfQQBZXZMNvlD9MoLFrGCxto_yg&s"
                    ),
                    InstChild(
                        "1",
                        "IMAGE",
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTscOhKszrDl-442szGJ1p526Qu-J-DlOaMJmZRPczCTtQqPs0Zl7cJNMUNfFt4o843nIY&usqp=CAU"
                    ),
                )
            ),
        )
    }
}