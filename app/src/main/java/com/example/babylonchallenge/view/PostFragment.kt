package com.example.babylonchallenge.view


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.babylonchallenge.PostViewModel
import com.example.babylonchallenge.PostViewModelFactory

import com.example.babylonchallenge.R
import com.example.babylonchallenge.di.DaggerAppComponent
import com.example.babylonchallenge.di.NetworkModule
import com.example.babylonchallenge.model.Post
import kotlinx.android.synthetic.main.fragment_post.*
import javax.inject.Inject


class PostFragment : Fragment() {

    @Inject
    lateinit var postViewModelFactory: PostViewModelFactory

    private lateinit var viewmodel:PostViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DaggerAppComponent.builder()
            .networkModule(NetworkModule())
            .build()
            .inject(this)

        viewmodel = ViewModelProviders.of(this,postViewModelFactory).get(PostViewModel::class.java)
        viewmodel.postInfo()
        val getpostInfo:MutableLiveData<List<Post>>?= viewmodel.postData()
        getpostInfo?.observe(this,Observer<List<Post>>{
            t->
            Log.d("posttitle",t[0].title)
            postadapter(t)
        })
    }

    private fun postadapter(t:List<Post>){
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = PostAdapter(t,object : onPostClickListener{
            override fun onPostClicked(post: Post) {
                val fragmentManager = activity?.supportFragmentManager
                val transaction = fragmentManager?.beginTransaction()
                val args = Bundle()
                args.putInt("postId",post.id)
                args.putInt("userId",post.userId)
                Toast.makeText(context,"clicked",Toast.LENGTH_SHORT).show()
                val userPostFragment = PostInfoUserFragment()
                userPostFragment.arguments = args
                transaction?.replace(R.id.fragmentContainer,userPostFragment)
                    ?.addToBackStack(null)
                    ?.commit()
            }
        })
    }

}
