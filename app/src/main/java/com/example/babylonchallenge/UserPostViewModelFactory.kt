package com.example.babylonchallenge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.babylonchallenge.network.WebServices

class UserPostViewModelFactory(private val clientInterface:WebServices):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PostDetailViewModel(clientInterface) as T
    }
}