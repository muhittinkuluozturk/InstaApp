package com.example.instaapp
import org.junit.After
import org.junit.Before
import org.mockito.MockitoAnnotations

open class BaseTest {

    private lateinit var closeable: AutoCloseable

    @Before
    open fun setUp() {
        closeable = MockitoAnnotations.openMocks(this)
    }

    @After
    open fun tearDown() {
        closeable.close()
    }
}