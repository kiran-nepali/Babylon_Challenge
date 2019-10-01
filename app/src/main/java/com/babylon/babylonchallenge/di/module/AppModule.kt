package com.babylon.babylonchallenge.di.module

import com.babylon.babylonchallenge.PostDetailViewModelFactory
import com.babylon.babylonchallenge.PostViewModelFactory
import com.babylon.babylonchallenge.common.Constants
import com.babylon.babylonchallenge.data.repository.PostRepository
import com.babylon.babylonchallenge.data.repository.PostRepositoryImpl
import com.babylon.babylonchallenge.network.WebServices
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideClientInterface(retrofit: Retrofit): WebServices {
        return retrofit.create(WebServices::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideHttPInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun providePostRepository(webServices: WebServices): PostRepository {
        return PostRepositoryImpl(webServices)
    }

    @Provides
    @Singleton
    fun providePostViewModelFactory(postRepository: PostRepository): PostViewModelFactory {
        return PostViewModelFactory(postRepository)
    }

    @Provides
    @Singleton
    fun provideUserPostViewModelFactory(postRepository: PostRepository): PostDetailViewModelFactory {
        return PostDetailViewModelFactory(postRepository)
    }

}