package com.example.babylonchallenge

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.babylonchallenge.data.model.Post
import com.example.babylonchallenge.data.repository.PostRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PostViewModel @Inject constructor(private val postRepository: PostRepository) : ViewModel() {

    val postData: MutableLiveData<List<Post>> = MutableLiveData()
    private val compositeDisposable = CompositeDisposable()
    var errors: MutableLiveData<String> = MutableLiveData()
    var loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    fun getPosts() {
        compositeDisposable.add(
            postRepository.getPosts()
                .doOnSubscribe {
                    loadingLiveData.value = true
                }
                .subscribe({ posts ->
                    loadingLiveData.value = false
                    postData.value = posts
                }, {
                    loadingLiveData.value = false
                    errors.value = it.localizedMessage
                })
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}