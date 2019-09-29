package com.example.babylonchallenge.network

import com.example.babylonchallenge.model.Post
import com.example.babylonchallenge.model.comments.Comments
import com.example.babylonchallenge.model.users.Users
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GetRequest {

    @GET("posts")
    fun getPostRequest():Observable<List<Post>>

    @GET("posts")
    fun getPostInfoRequest(@Query("id") id:Int):Observable<List<Post>> //Querying particular post with post id

    @GET("users")
    fun getUserInfoRequest(@Query("id") id:Int):Observable<List<Users>>//Querying particular user with userid

    @GET("comments")
    fun getNumOfCommentsRequest(@Query("postId") postId:Int):Observable<List<Comments>>//Querying comment of that id

}