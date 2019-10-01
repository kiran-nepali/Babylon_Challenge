package com.example.babylonchallenge.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.babylonchallenge.R
import com.example.babylonchallenge.data.model.Post

class PostAdapter(private val listener: PostClickListener) :
    RecyclerView.Adapter<PostViewHolder>() {
    private var posts: List<Post> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.post_view_holder,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position], listener)
    }

    fun setItems(posts: List<Post>) {
        this@PostAdapter.posts = posts
        notifyDataSetChanged()
    }

}