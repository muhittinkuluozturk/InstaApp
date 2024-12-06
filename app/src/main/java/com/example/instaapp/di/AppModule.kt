package com.example.instaapp.di

import com.example.instaapp.data.network.InstApiService
import com.example.instaapp.data.repository.InstRepository
import com.example.instaapp.data.repository.InstRepositoryImpl
import com.example.instaapp.ui.detail.domain.GetPostCommentsUseCase
import com.example.instaapp.ui.detail.domain.GetPostDetailUseCase
import com.example.instaapp.ui.detail.presentation.PostDetailViewModel
import com.example.instaapp.ui.home.domain.GetUserMediaUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.example.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideInstApiService(retrofit: Retrofit): InstApiService {
        return retrofit.create(InstApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideInstRepository(apiService: InstApiService): InstRepository {
        return InstRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideGetUserMedia(repository: InstRepository): GetUserMediaUseCase {
        return GetUserMediaUseCase(repository)
    }

    @Provides
    fun provideGetPostDetailUseCase(repository: InstRepository): GetPostDetailUseCase {
        return GetPostDetailUseCase(repository)
    }

    @Provides
    fun provideGetPostCommentsUseCase(repository: InstRepository): GetPostCommentsUseCase {
        return GetPostCommentsUseCase(repository)
    }

    @Provides
    fun providePostDetailViewModel(
        getPostDetailUseCase: GetPostDetailUseCase,
        getPostCommentsUseCase: GetPostCommentsUseCase
    ): PostDetailViewModel {
        return PostDetailViewModel(getPostDetailUseCase, getPostCommentsUseCase)
    }
}