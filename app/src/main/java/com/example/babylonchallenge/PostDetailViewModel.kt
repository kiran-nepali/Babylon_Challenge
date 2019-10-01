package com.example.babylonchallenge

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.babylonchallenge.data.model.Post
import com.example.babylonchallenge.data.model.comments.Comments
import com.example.babylonchallenge.data.model.users.Users
import com.example.babylonchallenge.data.repository.PostRepository
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class PostDetailViewModel @Inject constructor(private val repository: PostRepository) : ViewModel() {

    var userpostinfo: MutableLiveData<List<Post>> = MutableLiveData()
    var userIdinfo: MutableLiveData<List<Users>> = MutableLiveData()
    var commentinfo: MutableLiveData<List<Comments>> = MutableLiveData()
    val compositeDisposable = CompositeDisposable()

    fun individualPost(postId: Int) {
        val call: Observable<List<Post>> = repository.getPostInfo(postId)
        call
            .subscribe(IndividualPostObserver())
    }

    private fun IndividualPostObserver(): Observer<List<Post>> {
        return object : Observer<List<Post>> {
            override fun onComplete() {
                Log.d("IndividualPostEmission", "All items emitted")
            }

            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
            }

            override fun onNext(t: List<Post>) {
                userpostinfo?.value = t
            }

            override fun onError(e: Throwable) {
                Log.d("IndividualPostErrormsg", e.message)
            }
        }
    }

    fun userpostinfo(): MutableLiveData<List<Post>> {
        return userpostinfo
    }

    fun getUserId(userId: Int) {
        val call: Observable<List<Users>> = repository.getUserInfo(userId)
        call
            .subscribe(IndividualUserObserver())
    }

    private fun IndividualUserObserver(): Observer<List<Users>> {
        return object : Observer<List<Users>> {
            override fun onComplete() {
                Log.d("Users", "All items emmited")
            }

            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
            }

            override fun onNext(t: List<Users>) {
                userIdinfo?.value = t
            }

            override fun onError(e: Throwable) {
                Log.d("UserObserverError", e.message)
            }
        }
    }

    fun userIdInfo(): MutableLiveData<List<Users>> {
        return userIdinfo
    }

    fun getComments(postId: Int) {
        val call: Observable<List<Comments>> = repository.getNumOfComments(postId)
        call
            .subscribe(CommentObserver())
    }

    private fun CommentObserver(): Observer<List<Comments>> {
        return object : Observer<List<Comments>> {
            override fun onComplete() {
                Log.d("CommentsObserver", "All items emitted")
            }

            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
            }

            override fun onNext(t: List<Comments>) {
                commentinfo.value = t
            }

            override fun onError(e: Throwable) {
                Log.d("CommentError", e.message)
            }
        }
    }

    fun getCommentsInfo(): MutableLiveData<List<Comments>> {
        return commentinfo
    }
}