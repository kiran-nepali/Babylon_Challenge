package com.example.babylonchallenge.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.babylonchallenge.data.model.Post
import kotlinx.android.synthetic.main.post_view_holder.view.*


class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(post: Post, listener: PostClickListener) {
        itemView.tv_title.text = post.title
        itemView.setOnClickListener {
            listener.onPostClicked(post)
        }
    }
}