package com.example.babylonchallenge.network

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GetRequest {

    @GET("posts")
    fun getPostRequest():Observable<>
}