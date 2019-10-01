package com.babylon.babylonchallenge.view

import com.babylon.babylonchallenge.data.model.Post

interface PostClickListener {
    fun onPostClicked(post: Post)
}