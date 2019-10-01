package com.example.babylonchallenge

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.babylonchallenge.data.model.Post
import com.example.babylonchallenge.data.repository.PostRepository
import com.example.babylonchallenge.network.WebServices
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PostViewModel @Inject constructor(private val postRepository: PostRepository) : ViewModel() {

    val postData: MutableLiveData<List<Post>> = MutableLiveData()
    private val compositeDisposable = CompositeDisposable()
    var errors: MutableLiveData<String> = MutableLiveData()
    fun getPosts() {
        compositeDisposable.add(
            postRepository.getPosts()
                .subscribe({ posts ->
                    postData.value = posts
                }, {
                    errors.value = it.localizedMessage
                })
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}