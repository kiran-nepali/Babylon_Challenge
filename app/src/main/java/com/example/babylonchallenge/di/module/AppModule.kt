package com.example.babylonchallenge.di.module

import com.example.babylonchallenge.PostViewModelFactory
import com.example.babylonchallenge.PostDetailViewModelFactory
import com.example.babylonchallenge.common.Constants
import com.example.babylonchallenge.data.repository.PostRepository
import com.example.babylonchallenge.data.repository.PostRepositoryImpl
import com.example.babylonchallenge.network.WebServices
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
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideHttPInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
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