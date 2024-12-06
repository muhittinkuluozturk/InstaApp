package com.example.instaapp.data.repository

import com.example.instaapp.data.network.InstApiService
import com.example.instaapp.ui.detail.data.model.InstComment
import com.example.instaapp.ui.detail.data.model.InstPostDetail
import com.example.instaapp.ui.home.data.model.InstChild
import com.example.instaapp.ui.home.data.model.InstPost
import javax.inject.Inject

class InstRepositoryImpl @Inject constructor(
    private val apiService: InstApiService
) : InstRepository {
    override suspend fun getUserMedia(userId: String, accessToken: String): List<InstPost> {
        return apiService.getUserMedia(userId, accessToken)
    }

    override suspend fun getPostDetail(postId: String, accessToken: String): InstPostDetail {
        return apiService.getPostDetail(postId, "id,caption,media_type,media_url,timestamp,username,comments_count,like_count", accessToken)
    }

    override suspend fun getPostComments(postId: String, accessToken: String): List<InstComment> {
        return apiService.getPostComments(postId, accessToken = accessToken)
    }

    //TODO Mock data denemeleri için eklendi. KAldırılacak
    override suspend fun getUserMediaMock(userId: String, accessToken: String): List<InstPost> {
        return listOf(
            InstPost(
                "1",
                "Caption 1",
                "IMAGE",
                "https://www.dpzone.in/wp-content/uploads/1/17389780.jpg",
                "1672531200"
            ),
            InstPost(
                "2",
                "Caption 1",
                "IMAGE",
                "https://www.hlimg.com/images/stories/738X538/instagram-caption-post_1721823115-7202e.jpg",
                "1672531200"
            ),
            InstPost(
                "3",
                "Caption 1",
                "IMAGE",
                "https://rawmalroams.com/wp-content/uploads/2021/09/shutterstock_111732092-2-1.jpg",
                "1672531200"
            ),
            InstPost(
                "4",
                "Caption 2",
                "VIDEO",
                "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4",
                "1672531200"
            ),
            InstPost(
                "5",
                "Caption 1",
                "IMAGE",
                "https://i.pinimg.com/736x/c3/af/77/c3af7794b4b167868bc1c882aeb90218.jpg",
                "1672531200"
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
                        "https://hips.hearstapps.com/hmg-prod/images/cat-captions-for-instagram-6595a058e956b.jpeg?crop=0.588xw:1.00xh;0.207xw,0&resize=1200:*",
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

                ),
                timestamp = "1672531200"
            ),
        )
    }

    //TODO Mock data denemeleri için eklendi. KAldırılacak
    override suspend fun getPostDetailMock(postId: String, accessToken: String): InstPostDetail {
        return InstPostDetail(
            post = InstPost(
                id = "1",
                caption = "Exploring the beauty of nature 🌲🌄 #Adventure",
                media_type = "IMAGE",
                media_url = "https://rawmalroams.com/wp-content/uploads/2021/09/shutterstock_111732092-2-1.jpg",
                timestamp = "1672531200",
                children = listOf(
                    InstChild(
                        id = "1_1",
                        media_type = "IMAGE",
                        media_url = "https://rawmalroams.com/wp-content/uploads/2021/09/shutterstock_111732092-2-1.jpg"
                    ),
                    InstChild(
                        id = "1_2",
                        media_type = "IMAGE",
                        media_url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT50notvqBCfQQBZXZMNvlD9MoLFrGCxto_yg&s"
                    )
                ),
                username = "nature_lover",
                commentsCount = 45,
                likesCount = 1200,
                userProfilePicture = "https://example.com/user_profile.jpg"
            ),
            comments = listOf(
                InstComment(
                    id = "c1",
                    text = "Amazing shot! 🌟",
                    username = "adventure_fan",
                    timestamp = "1672531200",
                    userProfilePicture = "https://example.com/comment_user1.jpg"
                ),
                InstComment(
                    id = "c2",
                    text = "Love the vibes! ❤️",
                    username = "wanderlust_gal",
                    timestamp = "1672531200",
                    userProfilePicture = "https://example.com/comment_user2.jpg"
                )
            )
        )
    }
}