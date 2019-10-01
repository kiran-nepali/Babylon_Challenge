package com.example.babylonchallenge

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.babylonchallenge.model.Post
import com.example.babylonchallenge.model.comments.Comments
import com.example.babylonchallenge.model.users.Users
import com.example.babylonchallenge.network.WebServices
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostDetailViewModel @Inject constructor(val clientInterface:WebServices):ViewModel() {

    var userpostinfo:MutableLiveData<List<Post>> = MutableLiveData()
    var userIdinfo:MutableLiveData<List<Users>> = MutableLiveData()
    var commentinfo:MutableLiveData<List<Comments>> = MutableLiveData()
    val compositeDisposable = CompositeDisposable()

    fun individualPost(postId:Int){
        val call:Observable<List<Post>> = clientInterface.getPostInfoRequest(postId)
        call
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(IndividualPostObserver())
    }

    private fun IndividualPostObserver():Observer<List<Post>>{
        return object: Observer<List<Post>>{
            override fun onComplete() {
                Log.d("IndividualPostEmission","All items emitted")
            }

            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
            }

            override fun onNext(t: List<Post>) {
                userpostinfo?.value = t
            }

            override fun onError(e: Throwable) {
                Log.d("IndividualPostErrormsg",e.message)
            }
        }
    }

    fun userpostinfo():MutableLiveData<List<Post>>{
        return userpostinfo
    }

    fun getUserId(userId:Int){
        val call:Observable<List<Users>> = clientInterface.getUserInfoRequest(userId)
        call
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(IndividualUserObserver())
    }

    private fun IndividualUserObserver():Observer<List<Users>>{
        return object: Observer<List<Users>>{
            override fun onComplete() {
                Log.d("Users","All items emmited")
            }

            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
            }

            override fun onNext(t: List<Users>) {
                userIdinfo?.value = t
            }

            override fun onError(e: Throwable) {
                Log.d("UserObserverError",e.message)
            }
        }
    }

    fun userIdInfo():MutableLiveData<List<Users>>{
        return userIdinfo
    }

    fun getComments(postId: Int){
        val call:Observable<List<Comments>> = clientInterface.getNumOfCommentsRequest(postId)
        call
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(CommentObserver())
    }

    private fun CommentObserver():Observer<List<Comments>>{
        return object: Observer<List<Comments>>{
            override fun onComplete() {
                Log.d("CommentsObserver","All items emitted")
            }

            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
            }

            override fun onNext(t: List<Comments>) {
                commentinfo?.value = t
            }

            override fun onError(e: Throwable) {
                Log.d("CommentError",e.message)
            }
        }
    }

    fun getCommentsInfo():MutableLiveData<List<Comments>>{
        return commentinfo
    }
}