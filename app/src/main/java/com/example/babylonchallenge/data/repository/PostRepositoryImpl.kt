package com.example.babylonchallenge.data.repository

import com.example.babylonchallenge.network.WebServices
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(private val webServices: WebServices) : PostRepository{


    override fun getPosts() =
        webServices.getPosts().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

    override fun getPostInfo(id: Int) =
        webServices.getPostInfo(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

    override fun getUserInfo(id: Int) =
        webServices.getUserInfo(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())


    override fun getNumOfComments(postId: Int) =
        webServices.getNumOfComments(postId).subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread()
        )

}