package com.example.babylonchallenge

import com.example.babylonchallenge.network.WebServices
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PostViewModelTest {


    @Mock
    lateinit var webServices: WebServices

    private lateinit var postViewModel: PostViewModel


    @Before
    fun setUp() {
        this.postViewModel = PostViewModel(webServices)
    }

    @Test
    fun getPostObserver() {
    }


    @Test
    fun postInfo() {
        `when`(webServices.getPosts()).thenReturn(Single.just(emptyList()))
//        val postobserver = mock(Observer::class.java) as Observer<List<Post>>
//        this.postViewModel.postData?.observeForever(postobserver)
//        this.postViewModel.postInfo()
//        assertNotNull(this.postViewModel.postData?.value)
    }


}