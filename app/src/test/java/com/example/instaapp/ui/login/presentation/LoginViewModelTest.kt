package com.example.instaapp.ui.login.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.instaapp.BaseTest
import com.example.instaapp.data.model.User
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class LoginViewModelTest : BaseTest() {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var loginViewModel: LoginViewModel

    private val mockUser = User(id = "1", name = "Test User", token = "test_token")

    @Before
    override fun setUp() {
        super.setUp()
        loginViewModel = LoginViewModel()
    }

    @Test
    fun testLoginUpdatesUser() {
        val observer = mock(Observer::class.java) as Observer<User>
        loginViewModel.user.observeForever(observer)

        loginViewModel._user.value = mockUser
        verify(observer).onChanged(mockUser)

        assertEquals(mockUser, loginViewModel.user.value)

        loginViewModel.user.removeObserver(observer)
    }
}
