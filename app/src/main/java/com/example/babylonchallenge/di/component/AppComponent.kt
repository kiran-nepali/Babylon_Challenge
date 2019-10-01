package com.example.babylonchallenge.di.component

import com.example.babylonchallenge.di.module.AppModule
import com.example.babylonchallenge.view.PostsFragment
import com.example.babylonchallenge.view.PostDetailFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(postsFragment: PostsFragment)
    fun inject(postDetailFragment: PostDetailFragment)
}