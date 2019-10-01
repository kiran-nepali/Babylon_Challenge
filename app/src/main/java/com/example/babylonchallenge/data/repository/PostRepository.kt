package com.example.babylonchallenge.data.repository

import com.example.babylonchallenge.data.model.Post
import com.example.babylonchallenge.data.model.comments.Comments
import com.example.babylonchallenge.data.model.users.Users
import io.reactivex.Observable
import io.reactivex.Single

interface PostRepository{
    fun getPosts() : Single<List<Post>>

    fun getPostInfo(id: Int) : Single<List<Post>>

    fun getUserInfo(id: Int) : Single<List<Users>>


    fun getNumOfComments(postId: Int): Single<List<Comments>>
}