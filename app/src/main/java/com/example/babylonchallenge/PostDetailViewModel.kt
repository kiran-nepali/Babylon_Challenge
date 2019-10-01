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

    val userpostinfo: MutableLiveData<List<Post>> = MutableLiveData()
    var errors: MutableLiveData<String> = MutableLiveData()
    val userIdinfo: MutableLiveData<List<Users>> = MutableLiveData()
    val commentinfo: MutableLiveData<List<Comments>> = MutableLiveData()
    private val compositeDisposable = CompositeDisposable()

    fun individualPost(postId: Int) {
        compositeDisposable.add(
        repository.getPostInfo(postId)
            .subscribe({posts->
                userpostinfo.value = posts
            },{
                error->
                errors.value = error.localizedMessage
            })
        )
    }

    fun getUserId(userId: Int) {
        compositeDisposable.add(
        repository.getUserInfo(userId)
            .subscribe({
                user ->
                userIdinfo.value = user
            },{
                error ->
                errors.value = error.localizedMessage
            })
        )
    }

    fun getComments(postId: Int) {
        compositeDisposable.add(
        repository.getNumOfComments(postId)
            .subscribe({
                comments->
                commentinfo.value = comments
            },{
                error->
                errors.value = error.localizedMessage
            })
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}