package com.example.babylonchallenge.network

import com.example.babylonchallenge.data.model.Post
import com.example.babylonchallenge.data.model.comments.Comments
import com.example.babylonchallenge.data.model.users.Users
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {

    @GET("posts")
    fun getPosts():Single<List<Post>>

    @GET("posts")
    fun getPostInfo(@Query("id") id:Int):Single<List<Post>>

    @GET("users")
    fun getUserInfo(@Query("id") id:Int):Single<List<Users>>

    @GET("comments")
    fun getNumOfComments(@Query("postId") postId:Int):Single<List<Comments>>

}