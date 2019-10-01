package com.babylon.babylonchallenge.di.component

import com.babylon.babylonchallenge.di.module.AppModule
import com.babylon.babylonchallenge.view.PostsFragment
import com.babylon.babylonchallenge.view.PostDetailFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(postsFragment: PostsFragment)
    fun inject(postDetailFragment: PostDetailFragment)
}