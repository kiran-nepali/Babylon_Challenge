package com.example.babylonchallenge.view


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.example.babylonchallenge.R
import com.example.babylonchallenge.PostDetailViewModel
import com.example.babylonchallenge.PostDetailViewModelFactory
import com.example.babylonchallenge.di.component.DaggerAppComponent
import com.example.babylonchallenge.di.module.AppModule
import com.example.babylonchallenge.data.model.Post
import com.example.babylonchallenge.data.model.comments.Comments
import com.example.babylonchallenge.data.model.users.Users
import kotlinx.android.synthetic.main.fragment_post_info_user.*
import javax.inject.Inject


class PostInfoUserFragment : Fragment() {

    @Inject
    lateinit var postDetailViewModelFactory: PostDetailViewModelFactory
    private lateinit var postDetailViewModel: PostDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_post_info_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DaggerAppComponent.builder()
            .appModule(AppModule())
            .build()
            .inject(this)

        val postIdfromBundle = arguments?.getInt("postId")
        postDetailViewModel = ViewModelProviders.of(this,postDetailViewModelFactory).get(PostDetailViewModel::class.java)
        if (postIdfromBundle != null) {
            postDetailViewModel.individualPost(postIdfromBundle)
        }
        postDetailViewModel.userpostinfo.observe(this,Observer<List<Post>>{
            post->
            tv_postTitle.text = post[0].title
            tv_postBody.text = post[0].body
        })

        val userIdFromBundle = arguments?.getInt("userId")
        if (userIdFromBundle != null) {
            postDetailViewModel.getUserId(userIdFromBundle)
        }
        postDetailViewModel.userIdinfo.observe(this,Observer<List<Users>>{
            username->
            tv_authorName.text = username[0].name
        })
//

        if (postIdfromBundle != null) {
            postDetailViewModel.getComments(postIdfromBundle)
        }
        postDetailViewModel.commentinfo.observe(this,Observer<List<Comments>>{
            numOfcomments->
            tv_comments.text = numOfcomments.size.toString()
        })


    }

}
