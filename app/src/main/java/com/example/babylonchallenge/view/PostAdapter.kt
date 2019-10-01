package com.example.babylonchallenge.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.babylonchallenge.R
import com.example.babylonchallenge.data.model.Post
import kotlinx.android.synthetic.main.cardview.view.*

class PostAdapter(private val listener:PostClickListener):RecyclerView.Adapter<PostViewHolder>() {
    private var posts: List<Post> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cardview,parent,false))
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position],listener)
    }
    fun setItems(posts: List<Post>){
        this@PostAdapter.posts = posts
        notifyDataSetChanged()
    }

}

class PostViewHolder(view: View):RecyclerView.ViewHolder(view){
    fun bind(post: Post,listener: PostClickListener){
        itemView.tv_title.text = post.title
        itemView.setOnClickListener {
            listener.onPostClicked(post)
        }
    }
}

interface PostClickListener{
    fun onPostClicked(post:Post)
}