package com.example.babylonchallenge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.babylonchallenge.network.WebServices

class PostViewModelFactory(private val clientInterface:WebServices):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PostViewModel(clientInterface) as T
    }

}