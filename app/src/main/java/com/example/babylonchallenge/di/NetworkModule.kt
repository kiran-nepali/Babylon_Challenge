package com.example.babylonchallenge.di

import com.example.babylonchallenge.PostViewModelFactory
import com.example.babylonchallenge.common.Constants
import com.example.babylonchallenge.network.GetRequest
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideClientInterface(retrofit: Retrofit):GetRequest{
        return retrofit.create(GetRequest::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor):OkHttpClient{
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideHttPIntercepter():HttpLoggingInterceptor{
        return HttpLoggingInterceptor()
    }

    @Provides
    @Singleton
    fun providePostViewModelFactory(clientInterface:GetRequest):PostViewModelFactory{
        return PostViewModelFactory(clientInterface)
    }


}