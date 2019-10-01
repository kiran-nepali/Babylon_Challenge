package com.example.babylonchallenge.di.component

import com.example.babylonchallenge.di.module.AppModule
import com.example.babylonchallenge.view.PostFragment
import com.example.babylonchallenge.view.PostInfoUserFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(postFragment: PostFragment)
    fun inject(postInfoUserFragment: PostInfoUserFragment)
}