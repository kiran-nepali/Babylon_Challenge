package com.example.babylonchallenge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.babylonchallenge.network.GetRequest

class PostViewModelFactory(private val clientInterface:GetRequest):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PostViewModel(clientInterface) as T
    }

}