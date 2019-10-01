package com.example.babylonchallenge.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.babylonchallenge.PostDetailViewModel
import com.example.babylonchallenge.PostDetailViewModelFactory
import com.example.babylonchallenge.R
import com.example.babylonchallenge.data.model.Post
import com.example.babylonchallenge.di.component.DaggerAppComponent
import com.example.babylonchallenge.di.module.AppModule
import kotlinx.android.synthetic.main.fragment_post_info_user.*
import javax.inject.Inject


class PostDetailFragment : Fragment() {

    @Inject
    lateinit var postDetailViewModelFactory: PostDetailViewModelFactory
    private lateinit var postDetailViewModel: PostDetailViewModel

    lateinit var post: Post
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

        post = arguments!!.getParcelable("post")!!
        tvPostTitle.text = post.title
        tvPostBody.text = post.body


        postDetailViewModel = ViewModelProviders.of(this, postDetailViewModelFactory)
            .get(PostDetailViewModel::class.java)


        postDetailViewModel.usersLiveData.observe(this, Observer<String> { username ->
            tvAuthorName.text = username
        })
        postDetailViewModel.getUserName(post.userId)

        postDetailViewModel.numberOfComments.observe(
            this,
            Observer<Int> { numberOfComments ->
                tv_comments.text = numberOfComments.toString()
            })
        postDetailViewModel.getComments(post.id)

    }

}
