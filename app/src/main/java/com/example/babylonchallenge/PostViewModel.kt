package com.example.babylonchallenge

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.babylonchallenge.model.Post
import com.example.babylonchallenge.network.GetRequest
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostViewModel @Inject constructor(val clientInterface: GetRequest) : ViewModel(){

    val postObserver = PostObserver()
    var postData:MutableLiveData<List<Post>>?= MutableLiveData()
    val compositeDisposable = CompositeDisposable()
    fun postInfo() {
        val call: Observable<List<Post>> = clientInterface.getPostRequest()
        call
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(postObserver)
    }

    private fun PostObserver():Observer<List<Post>>{
        return object: Observer<List<Post>>{
            override fun onComplete() {
                Log.d("Emit","All items emitted")
            }

            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
            }

            override fun onNext(t: List<Post>) {
                postData?.value = t
            }

            override fun onError(e: Throwable) {
                Log.d("postError",e.message)
            }
        }
    }

    fun postData():MutableLiveData<List<Post>>?{
        return postData
    }
}