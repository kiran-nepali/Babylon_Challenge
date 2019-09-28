package com.example.babylonchallenge.network

import com.example.babylonchallenge.model.Post
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GetRequest {

    @GET("posts")
    fun getPostRequest():Observable<List<Post>>
}