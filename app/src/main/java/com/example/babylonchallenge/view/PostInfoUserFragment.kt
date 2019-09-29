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
import com.example.babylonchallenge.UserPostViewModel
import com.example.babylonchallenge.UserPostViewModelFactory
import com.example.babylonchallenge.di.DaggerAppComponent
import com.example.babylonchallenge.di.NetworkModule
import com.example.babylonchallenge.model.Post
import com.example.babylonchallenge.model.users.Users
import kotlinx.android.synthetic.main.fragment_post_info_user.*
import javax.inject.Inject


class PostInfoUserFragment : Fragment() {

    @Inject
    lateinit var userPostViewModelFactory: UserPostViewModelFactory
    private lateinit var userPostViewModel: UserPostViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post_info_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DaggerAppComponent.builder()
            .networkModule(NetworkModule())
            .build()
            .inject(this)

        val postIdfromBundle = arguments?.getInt("postId")
        userPostViewModel = ViewModelProviders.of(this,userPostViewModelFactory).get(UserPostViewModel::class.java)
        if (postIdfromBundle != null) {
            userPostViewModel.individualPost(postIdfromBundle)
        }
        userPostViewModel.userpostinfo().observe(this,Observer<List<Post>>{
            t->
            Log.d("posttitle",t[0].title)
            tv_postTitle.text = t[0].title
            tv_postBody.text = t[0].body
        })

        val userIdFromBundle = arguments?.getInt("userId")
        if (userIdFromBundle != null) {
            userPostViewModel.getUserId(userIdFromBundle)
        }
        userPostViewModel.userIdInfo().observe(this,Observer<List<Users>>{
            t->
            tv_authorName.text = t[0].name
        })

    }

}
