package com.example.babylonchallenge.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.babylonchallenge.PostViewModel
import com.example.babylonchallenge.PostViewModelFactory
import com.example.babylonchallenge.R
import com.example.babylonchallenge.data.model.Post
import com.example.babylonchallenge.di.component.DaggerAppComponent
import com.example.babylonchallenge.di.module.AppModule
import kotlinx.android.synthetic.main.fragment_post.*
import javax.inject.Inject


class PostsFragment : Fragment() {

    @Inject
    lateinit var postViewModelFactory: PostViewModelFactory

    private lateinit var viewModel: PostViewModel

    private lateinit var postAdapter: PostAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DaggerAppComponent.builder()
            .appModule(AppModule())
            .build()
            .inject(this)

        viewModel = ViewModelProviders.of(this, postViewModelFactory).get(PostViewModel::class.java)
        viewModel.postData.observe(this, Observer<List<Post>> { posts ->
            btnRetry.visibility = View.GONE
            displayPosts(posts)
        })
        viewModel.errors.observe(this, Observer {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            btnRetry.visibility = View.VISIBLE
        })
        rvPosts.layoutManager = LinearLayoutManager(context)
        postAdapter = PostAdapter(object : PostClickListener {
            override fun onPostClicked(post: Post) {
                val fragmentManager = activity?.supportFragmentManager
                val transaction = fragmentManager?.beginTransaction()
                val args = Bundle()
                args.putParcelable("post", post)
                val userPostFragment = PostDetailFragment()
                userPostFragment.arguments = args
                transaction?.replace(R.id.fragmentContainer, userPostFragment)
                    ?.addToBackStack(null)
                    ?.commit()
            }
        })

        viewModel.loadingLiveData.observe(this, Observer { showProgressbar ->
            if (showProgressbar) {
                progressbar.visibility = View.VISIBLE
            } else {
                progressbar.visibility = View.GONE
            }
        })
        btnRetry.setOnClickListener {
            btnRetry.visibility = View.GONE
            viewModel.getPosts()
        }
        rvPosts.adapter = postAdapter

        viewModel.getPosts()


    }

    private fun displayPosts(posts: List<Post>) {
        postAdapter.setItems(posts)
    }

}
