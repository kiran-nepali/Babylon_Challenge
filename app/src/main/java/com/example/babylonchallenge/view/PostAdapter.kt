package com.example.babylonchallenge.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.babylonchallenge.R
import com.example.babylonchallenge.model.Post
import kotlinx.android.synthetic.main.cardview.view.*

class PostAdapter(private val post:List<Post>,val listener:onPostClickListener):RecyclerView.Adapter<PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cardview,parent,false))
    }

    override fun getItemCount(): Int {
        return post.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.title.text = post[position].title
        holder.bind(post[position],listener)
    }

}

class PostViewHolder(view: View):RecyclerView.ViewHolder(view){
    val title = view.tv_title
    fun bind(post: Post,listener: onPostClickListener){
        itemView.setOnClickListener {
            listener.onPostClicked(post)
        }
    }
}

interface onPostClickListener{
    fun onPostClicked(post:Post)
}