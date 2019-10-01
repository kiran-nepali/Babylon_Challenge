package com.example.babylonchallenge

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.babylonchallenge.model.Post
import com.example.babylonchallenge.network.WebServices
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostViewModel @Inject constructor(private val webServices: WebServices) : ViewModel() {

    val postData: MutableLiveData<List<Post>> = MutableLiveData()
    val compositeDisposable = CompositeDisposable()
    var errors: MutableLiveData<String> = MutableLiveData()
    fun getPosts() {
        compositeDisposable.add(
            webServices.getPosts().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ posts ->
                    postData.value = posts
                }, {
                    errors.value = it.localizedMessage
                })
        )
    }

}