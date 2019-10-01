package com.babylon.babylonchallenge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.babylon.babylonchallenge.data.repository.PostRepository

class PostDetailViewModelFactory(private val repository: PostRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PostDetailViewModel(repository) as T
    }
}