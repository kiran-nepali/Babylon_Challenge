package com.babylon.babylonchallenge.network

import com.babylon.babylonchallenge.data.model.Post
import com.babylon.babylonchallenge.data.model.Comment
import com.babylon.babylonchallenge.data.model.User
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {

    @GET("posts")
    fun getPosts(): Single<List<Post>>

    @GET("users")
    fun getUsers(@Query("id") id: Int): Single<List<User>>

    @GET("comments")
    fun getComments(@Query("postId") postId: Int): Single<List<Comment>>

}