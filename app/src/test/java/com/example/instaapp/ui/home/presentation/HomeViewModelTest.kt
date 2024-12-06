import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.instaapp.data.repository.InstRepository
import com.example.instaapp.ui.home.data.model.InstPost
import com.example.instaapp.ui.home.domain.GetUserMediaUseCase
import com.example.instaapp.ui.home.presentation.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var instRepository: InstRepository

    private lateinit var getUserMediaUseCase: GetUserMediaUseCase
    private lateinit var homeViewModel: HomeViewModel

    val mockInstPosts = listOf(
        InstPost(
            id = "1",
            caption = "Test Caption 1",
            media_type = "image",
            media_url = "http://example.com/image1.jpg",
            timestamp = "2023-10-01T12:00:00Z",
            children = listOf()
        ),
        InstPost(
            id = "2",
            caption = "Test Caption 2",
            media_type = "video",
            media_url = "http://example.com/video1.mp4",
            timestamp = "2023-10-02T12:00:00Z",
            children = listOf()
        )
    )

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getUserMediaUseCase = GetUserMediaUseCase(instRepository)
        homeViewModel = HomeViewModel(getUserMediaUseCase)
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    @Suppress("UNCHECKED_CAST")
    fun testLoadUserMediaSuccess() = runTest {
        val userId = "test_user_id"
        val accessToken = "test_access_token"

        `when`(getUserMediaUseCase(userId, accessToken)).thenReturn(Result.success(mockInstPosts))

        val observer = mock(Observer::class.java) as Observer<List<InstPost>>
        homeViewModel.posts.observeForever(observer)

        homeViewModel.loadUserMedia(userId, accessToken)

        advanceUntilIdle() // Wait for all coroutines to complete

        verify(observer).onChanged(mockInstPosts)
        Assert.assertEquals(mockInstPosts, homeViewModel.posts.value)

        homeViewModel.posts.removeObserver(observer)
    }

    @Test
    @Suppress("UNCHECKED_CAST")
    fun testLoadUserMediaFailure() = runTest {
        val userId = "test_user_id"
        val accessToken = "test_access_token"
        val exceptionMessage = "Network error"

        `when`(getUserMediaUseCase(userId, accessToken)).thenReturn(Result.failure(Exception(exceptionMessage)))

        val observer = mock(Observer::class.java) as Observer<String>
        homeViewModel.error.observeForever(observer)

        homeViewModel.loadUserMedia(userId, accessToken)

        advanceUntilIdle() // Wait for all coroutines to complete

        verify(observer).onChanged("Error fetching media: $exceptionMessage")
        Assert.assertEquals("Error fetching media: $exceptionMessage", homeViewModel.error.value)

        homeViewModel.error.removeObserver(observer)
    }
}