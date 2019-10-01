package com.example.babylonchallenge.data.repository

import com.example.babylonchallenge.network.WebServices
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(private val webServices: WebServices) : PostRepository{


    override fun getPosts() =
        webServices.getPosts().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

    override fun getUsers(id: Int) =
        webServices.getUsers(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

    override fun getComments(postId: Int) =
        webServices.getComments(postId).subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread()
        )

}