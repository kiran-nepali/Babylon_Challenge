package com.example.babylonchallenge.di

import com.example.babylonchallenge.view.PostFragment
import com.example.babylonchallenge.view.PostInfoUserFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(NetworkModule::class))
interface AppComponent {
    fun inject(postFragment: PostFragment)
    fun inject(postInfoUserFragment: PostInfoUserFragment)
}