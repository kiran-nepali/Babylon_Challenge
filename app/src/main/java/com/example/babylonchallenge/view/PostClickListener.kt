package com.example.babylonchallenge.view

import com.example.babylonchallenge.data.model.Post

interface PostClickListener {
    fun onPostClicked(post: Post)
}