package com.example.babylonchallenge.network

import com.example.babylonchallenge.data.model.Post
import com.example.babylonchallenge.data.model.comments.Comment
import com.example.babylonchallenge.data.model.users.User
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