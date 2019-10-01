package com.babylon.babylonchallenge

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.babylon.babylonchallenge.data.repository.PostRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PostDetailViewModel @Inject constructor(private val repository: PostRepository) :
    ViewModel() {

    var errorsLiveData: MutableLiveData<String> = MutableLiveData()
    val usersLiveData: MutableLiveData<String> = MutableLiveData()
    val numberOfComments: MutableLiveData<Int> = MutableLiveData()
    private val compositeDisposable = CompositeDisposable()

    fun getUserName(userId: Int) {
        compositeDisposable.add(
            repository.getUsers(userId)
                .subscribe({ users ->
                    if (users.isNotEmpty()) {
                        usersLiveData.value = users.first().name
                    } else {
                        errorsLiveData.value = "No User Found"
                    }
                }, { error ->
                    errorsLiveData.value = error.localizedMessage
                })
        )
    }

    fun getComments(postId: Int) {
        compositeDisposable.add(
            repository.getComments(postId)
                .subscribe({ comments ->
                    numberOfComments.value = comments.size
                }, { error ->
                    errorsLiveData.value = error.localizedMessage
                })
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}